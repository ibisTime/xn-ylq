package com.cdkj.ylq.ao.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.ICertRecordBO;
import com.cdkj.ylq.bo.ICertiBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAddressBook;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.InfoBasic;
import com.cdkj.ylq.domain.InfoContact;
import com.cdkj.ylq.domain.InfoOccupation;
import com.cdkj.ylq.domain.InfoZfb;
import com.cdkj.ylq.domain.InfoZqzn;
import com.cdkj.ylq.domain.MxCarrierNofification;
import com.cdkj.ylq.domain.MxReportData;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN623040Req;
import com.cdkj.ylq.dto.req.XN623041Req;
import com.cdkj.ylq.dto.req.XN623042Req;
import com.cdkj.ylq.dto.req.XN798650Req;
import com.cdkj.ylq.dto.res.XN623050Res;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.enums.EIDKind;
import com.cdkj.ylq.enums.EJourBizTypeBoss;
import com.cdkj.ylq.enums.EJourBizTypePlat;
import com.cdkj.ylq.enums.ESysUser;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;
import com.cdkj.ylq.http.BizConnecter;
import com.cdkj.ylq.http.HttpUtil;
import com.cdkj.ylq.http.JsonUtils;
import com.cdkj.ylq.tongdun.RiskServicePreloan;
import com.cdkj.ylq.tongdun.YunYingShang;

@Service
public class CertificationAOImpl implements ICertificationAO {

    protected static final Logger logger = LoggerFactory
        .getLogger(CertificationAOImpl.class);

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICertiBO certiBO;

    @Autowired
    private IApplyBO applyBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ICertRecordBO certRecordBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private RiskServicePreloan riskServicePreloan;

    @Autowired
    private YunYingShang yunYingShang;

    @Autowired
    private IBusinessManBO businessManBO;

    private String qiniu(String image) {
        StringBuilder str = new StringBuilder("http://");
        str.append(
            sysConfigBO.getStringValue(SysConstants.QINIU_DOMAIN,
                ESystemCode.YLQ.getCode())).append("/").append(image);
        logger.info(str.toString());
        return str.toString();
    }

    @Override
    @Transactional
    public InfoZqzn doZqznVerify(String userId, String frontImage,
            String backImage, String faceImage) {
        User user = userBO.getUser(userId);
        XN798650Req req = new XN798650Req();
        req.setFaceImage(qiniu(faceImage));
        req.setFrontImage(qiniu(frontImage));
        req.setBackImage(qiniu(backImage));
        req.setCompanyCode(ESystemCode.YLQ.getCode());
        req.setSystemCode(ESystemCode.YLQ.getCode());
        InfoZqzn infoZqzn = BizConnecter.getBizData("798650",
            JsonUtils.object2Json(req), InfoZqzn.class);
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_ZQZN);
        // 认证成功
        if (infoZqzn.getZqznInfoRealAuth().getVerifyStatus() == 1) {
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoZqzn));
                certification.setCerDatetime(new Date());
                certification.setRef("");
                certificationBO.refreshCertification(certification);
            }
            if (certificationBO.isCompleteCerti(userId)) {
                Apply apply = applyBO.getInCertApply(userId);
                if (apply != null) {
                    apply.setStatus(EApplyStatus.TO_APPROVE.getCode());
                    applyBO.refreshStatus(apply);
                }
            }
            userBO.refreshIdentity(userId, infoZqzn.getZqznInfoFront()
                .getName(), EIDKind.IDCard.getCode(), infoZqzn
                .getZqznInfoFront().getIdNo());
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "认证失败，失败原因为:" + infoZqzn.getZqznInfoRealAuth().getReason());
        }
        // BigDecimal fee = sysConfigBO.getBigDecimalValue(
        // ECertiKey.INFO_ZQZN.getCode(), user.getCompanyCode());
        Long id = certRecordBO.saveCertRecord(userId, new BigDecimal(1500),
            ECertiKey.INFO_ZQZN.getCode(), user.getCompanyCode());
        // 接口费用
        BusinessMan man = businessManBO.getBusinessManByCompanyCode(user
            .getCompanyCode());
        accountBO.transAmount(man.getUserId(), ESysUser.SYS_USER.getCode(),
            ECurrency.CNY.getCode(), new BigDecimal(1500),
            EJourBizTypeBoss.API.getCode(), EJourBizTypePlat.API.getCode(),
            EJourBizTypeBoss.API.getValue(), EJourBizTypePlat.API.getValue(),
            id.toString());
        return infoZqzn;
    }

    @Override
    public void submitInfoBasic(XN623040Req req) {
        InfoBasic data = getInfoBasic(req);
        Certification certification = certificationBO.getCertification(
            req.getUserId(), ECertiKey.INFO_BASIC);
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
        if (certificationBO.isCompleteCerti(req.getUserId())) {
            Apply apply = applyBO.getInCertApply(req.getUserId());
            if (apply != null) {
                apply.setStatus(EApplyStatus.TO_APPROVE.getCode());
                applyBO.refreshStatus(apply);
            }
        }
    }

    @Override
    public void submitInfoOccupation(XN623041Req req) {
        InfoOccupation data = getInfoOccupation(req);
        Certification certification = certificationBO.getCertification(
            req.getUserId(), ECertiKey.INFO_OCCUPATION);
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
        if (certificationBO.isCompleteCerti(req.getUserId())) {
            Apply apply = applyBO.getInCertApply(req.getUserId());
            if (apply != null) {
                apply.setStatus(EApplyStatus.TO_APPROVE.getCode());
                applyBO.refreshStatus(apply);
            }
        }
    }

    @Override
    public void submitInfoContact(XN623042Req req) {
        InfoContact data = getInfoContact(req);
        Certification certification = certificationBO.getCertification(
            req.getUserId(), ECertiKey.INFO_CONTACT);
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
        if (certificationBO.isCompleteCerti(req.getUserId())) {
            Apply apply = applyBO.getInCertApply(req.getUserId());
            if (apply != null) {
                apply.setStatus(EApplyStatus.TO_APPROVE.getCode());
                applyBO.refreshStatus(apply);
            }
        }
    }

    @Override
    @Transactional
    public MxReportData doCarrierVerify(String userId, String taskId) {
        MxReportData mxReportData = certiBO.doMxReportDataGet(taskId);
        if (mxReportData != null) {
            Certification certification = certificationBO.getCertification(
                userId, ECertiKey.INFO_CARRIER);
            Integer config = sysConfigBO
                .getIntegerValue(SysConstants.CARRIER_VALID_DAYS,
                    certification.getCompanyCode());
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(mxReportData.getReportData());
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef("");
                certificationBO.refreshCertification(certification);
            }
            Apply apply = applyBO.getCurrentApply(userId);
            if (apply != null
                    && EApplyStatus.TO_CERTI.getCode()
                        .equals(apply.getStatus())) {
                applyBO.toDoApprove(apply);
            }
        }
        return mxReportData;
    }

    @Override
    @Transactional
    public void doMxCarrierTaskSubmitCallback(MxCarrierNofification notification) {
        logger.info("&**&*&* 开始魔蝎回调——任务创建通知 &*&*&*&*&");
        String userId = notification.getUser_id();
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_CARRIER);
        if (certification != null) {
            certificationBO.refreshFlag(certification,
                ECertificationStatus.CERTING);
        }
    }

    @Override
    @Transactional
    public void doMxCarrierTaskCallback(MxCarrierNofification notification) {
        logger.info("&**&*&* 开始魔蝎回调——登录完成后通知 &*&*&*&*&");
        if (notification.isResult()) {
            String userId = notification.getUser_id();
            Certification certification = certificationBO.getCertification(
                userId, ECertiKey.INFO_CARRIER);
            if (certification != null) {
                certificationBO.refreshFlag(certification,
                    ECertificationStatus.CERTING);
            }
        } else {
            String userId = notification.getUser_id();
            Certification certification = certificationBO.getCertification(
                userId, ECertiKey.INFO_CARRIER);
            if (certification != null) {
                certificationBO.refreshFlag(certification,
                    ECertificationStatus.TO_CERTI);
            }
        }
    }

    @Override
    @Transactional
    public void doMxCarrierTaskFailCallback(MxCarrierNofification notification) {
        logger.info("&**&*&* 开始魔蝎回调——任务采集失败通知 &*&*&*&*&");
        String userId = notification.getUser_id();
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_CARRIER);
        if (certification != null) {
            certificationBO.refreshFlag(certification,
                ECertificationStatus.TO_CERTI);
        }
    }

    @Override
    @Transactional
    public void doMxCarrierReportCallback(MxCarrierNofification notification) {
        logger.info("&**&*&* 开始魔蝎回调——资信报告通知 &*&*&*&*&");
        String userId = notification.getUser_id();
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_CARRIER);
        Integer config = sysConfigBO.getIntegerValue(
            SysConstants.CARRIER_VALID_DAYS, certification.getCompanyCode());
        // 认证成功
        if (notification.isResult()) {
            String report = getMxReport(notification.getMobile(),
                notification.getTask_id(), certification.getCompanyCode());
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(notification));
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef(report);
                certificationBO.refreshCertification(certification);
            }
            Apply apply = applyBO.getCurrentApply(userId);
            if (apply != null
                    && EApplyStatus.TO_CERTI.getCode()
                        .equals(apply.getStatus())) {
                applyBO.toDoApprove(apply);
            }
        }
    }

    @Override
    public void doTdCarrierTaskSubmitCallback(String userId, String taskId) {
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_CARRIER);
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTING.getCode());
            certification.setResult(taskId);
            certificationBO.refreshCertification(certification);
        }
        // 测试使用
        // Integer config = sysConfigBO
        // .getIntegerValue(SysConstants.CARRIER_VALID_DAYS);
        // String report = "{\"test\":\"test\"}";
        // if (certification != null) {
        // certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
        // certification.setResult(report);
        // certification.setCerDatetime(new Date());
        // certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
        // DateUtil.getTodayStart(), config));
        // certification.setRef(report);
        // certificationBO.refreshCertification(certification);
        // }
        // Apply apply = applyBO.getCurrentApply(userId);
        // if (apply != null
        // && EApplyStatus.TO_CERTI.getCode().equals(apply.getStatus())) {
        // applyBO.toDoApprove(apply);
        // }
    }

    @Override
    @Transactional
    public void doTdCarrierTaskCompleteCallback(boolean isSuccess,
            String userId, String taskId) {
        Certification certification = certificationBO
            .getCarrierCertificationByTaskId(taskId);
        if (isSuccess) {
            Integer config = sysConfigBO
                .getIntegerValue(SysConstants.CARRIER_VALID_DAYS,
                    certification.getCompanyCode());
            String report = yunYingShang.query(taskId, userId);
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(report);
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef(report);
                certificationBO.refreshCertification(certification);
            }
            Apply apply = applyBO.getCurrentApply(userId);
            if (apply != null
                    && EApplyStatus.TO_CERTI.getCode()
                        .equals(apply.getStatus())) {
                applyBO.toDoApprove(apply);
            }
        } else {
            if (certification != null) {
                certificationBO.refreshFlag(certification,
                    ECertificationStatus.TO_CERTI);
            }
        }
    }

    @Override
    public void doAddressBookVerify(String userId,
            List<InfoAddressBook> addressBookList) {
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_ADDRESS_BOOK);
        Integer config = sysConfigBO.getIntegerValue(
            SysConstants.ADDRESS_BOOK_VALID_DAYS,
            certification.getCompanyCode());
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(addressBookList));
            certification.setCerDatetime(new Date());
            certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayStart(), config));
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
    }

    @Override
    public Paginable<Certification> queryCertificationPage(int start,
            int limit, Certification condition) {
        return certificationBO.getPaginable(start, limit, condition);
    }

    @Override
    public Certification getCertification(Long id) {
        return certificationBO.getCertification(id);
    }

    @Override
    @Transactional
    public XN623050Res getCertiInfo(String userId) {
        // 获取认证结果
        List<Certification> certifications = certificationBO
            .queryCertificationList(userId);
        if (CollectionUtils.isEmpty(certifications)) {
            throw new BizException("xn623000", "个人认证信息初始化失败");
        }
        // 组装认证结果信息
        XN623050Res res = transferCertiInfo(certifications);
        res.setUserId(userId);
        res.setUserInfo(userBO.getUser(userId));
        return res;
    }

    @Override
    public Object getCertiInfo(String userId, ECertiKey certiKey) {
        return null;
    }

    @Override
    public InfoAmount getMyCreditAmount(String userId) {
        InfoAmount infoAmount = null;
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        if (certification != null) {
            infoAmount = JsonUtil.json2Bean(certification.getResult(),
                InfoAmount.class);
            infoAmount.setFlag(certification.getFlag());
            if (ECertificationStatus.CERTI_YES.getCode().equals(
                certification.getFlag())) {
                infoAmount.setCerDatetime(certification.getCerDatetime());
                infoAmount.setValidDatetime(certification.getValidDatetime());
                infoAmount
                    .setValidDays(DateUtil.daysBetween(
                        DateUtil.getTodayStart(),
                        certification.getValidDatetime()));
            }
        }
        return infoAmount;
    }

    private XN623050Res transferCertiInfo(List<Certification> certifications) {
        XN623050Res res = new XN623050Res();
        res.setInfoBasicFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoOccupationFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoContactFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoCarrierFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoAddressBookFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoZfbFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoZqznFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoPersonalFlag(ECertificationStatus.TO_CERTI.getCode());

        for (Certification certification : certifications) {

            if (ECertiKey.INFO_BASIC.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoBasicFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    res.setInfoBasic(JsonUtil.json2Bean(
                        certification.getResult(), InfoBasic.class));
                }
            }
            if (ECertiKey.INFO_OCCUPATION.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoOccupationFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    res.setInfoOccupation(JsonUtil.json2Bean(
                        certification.getResult(), InfoOccupation.class));
                }
            }
            if (ECertiKey.INFO_CONTACT.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoContactFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    res.setInfoContact(JsonUtil.json2Bean(
                        certification.getResult(), InfoContact.class));
                }
            }

            if (ECertiKey.INFO_ZHIFUBAO.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoZfbFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    res.setInfoZfb(JsonUtil.json2Bean(
                        certification.getResult(), InfoZfb.class));
                }
            }

            if (ECertiKey.INFO_CARRIER.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoCarrierFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())
                        || ECertificationStatus.INVALID.getCode().equals(
                            certification.getFlag())) {
                    res.setInfoCarrier(certification.getResult());
                }
            }

            if (ECertiKey.INFO_ADDRESS_BOOK.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoAddressBookFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())
                        || ECertificationStatus.INVALID.getCode().equals(
                            certification.getFlag())) {
                    res.setInfoAddressBook(certification.getResult());
                }
            }

            if (ECertiKey.INFO_ZQZN.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoZqznFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())
                        || ECertificationStatus.INVALID.getCode().equals(
                            certification.getFlag())) {
                    res.setInfoZqznFlag(certification.getFlag());
                    res.setInfoZqzn(JsonUtil.json2Bean(
                        certification.getResult(), InfoZqzn.class));
                }
            }
            if (ECertiKey.INFO_PERSONAL.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoPersonalFlag(certification.getFlag());

            }
        }
        return res;
    }

    @Override
    public List<Certification> initialCertification(String userId) {
        List<Certification> certifications = new ArrayList<Certification>();
        User user = userBO.getUser(userId);
        String companyCode = user.getCompanyCode();

        // 活体认证
        Certification ZQZN = new Certification();
        ZQZN.setUserId(userId);
        ZQZN.setCertiKey(ECertiKey.INFO_ZQZN.getCode());
        ZQZN.setFlag(ECertificationStatus.TO_CERTI.getCode());
        ZQZN.setCompanyCode(companyCode);
        certificationBO.saveCertification(ZQZN);
        certifications.add(ZQZN);

        // 基本信息
        Certification certification1 = new Certification();
        certification1.setUserId(userId);
        certification1.setCertiKey(ECertiKey.INFO_BASIC.getCode());
        certification1.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certification1.setCompanyCode(companyCode);
        certificationBO.saveCertification(certification1);
        certifications.add(certification1);

        // 职业信息
        Certification certification2 = new Certification();
        certification2.setUserId(userId);
        certification2.setCertiKey(ECertiKey.INFO_OCCUPATION.getCode());
        certification2.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certification2.setCompanyCode(companyCode);
        certificationBO.saveCertification(certification2);
        certifications.add(certification2);

        // 紧急联系人信息
        Certification certification3 = new Certification();
        certification3.setUserId(userId);
        certification3.setCertiKey(ECertiKey.INFO_CONTACT.getCode());
        certification3.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certification3.setCompanyCode(companyCode);
        certificationBO.saveCertification(certification3);
        certifications.add(certification3);

        // 个人信息
        Certification personal = new Certification();
        personal.setUserId(userId);
        personal.setCertiKey(ECertiKey.INFO_PERSONAL.getCode());
        personal.setFlag(ECertificationStatus.TO_CERTI.getCode());
        personal.setCompanyCode(companyCode);
        certificationBO.saveCertification(personal);
        certifications.add(personal);
        // 运营商认证
        Certification carrier = new Certification();
        carrier.setUserId(userId);
        carrier.setCertiKey(ECertiKey.INFO_CARRIER.getCode());
        carrier.setFlag(ECertificationStatus.TO_CERTI.getCode());
        carrier.setCompanyCode(companyCode);
        certificationBO.saveCertification(carrier);
        certifications.add(carrier);

        // 通讯录认证
        Certification addressBook = new Certification();
        addressBook.setUserId(userId);
        addressBook.setCertiKey(ECertiKey.INFO_ADDRESS_BOOK.getCode());
        addressBook.setFlag(ECertificationStatus.TO_CERTI.getCode());
        addressBook.setCompanyCode(companyCode);
        certificationBO.saveCertification(addressBook);
        certifications.add(addressBook);

        // 支付宝认证
        Certification zfb = new Certification();
        zfb.setUserId(userId);
        zfb.setCertiKey(ECertiKey.INFO_ZHIFUBAO.getCode());
        zfb.setFlag(ECertificationStatus.TO_CERTI.getCode());
        zfb.setCompanyCode(companyCode);
        certificationBO.saveCertification(zfb);
        certifications.add(zfb);

        // 授信额度
        Certification creditAmount = new Certification();
        creditAmount.setUserId(userId);
        creditAmount.setCertiKey(ECertiKey.INFO_AMOUNT.getCode());
        creditAmount.setFlag(ECertificationStatus.TO_CERTI.getCode());
        creditAmount.setCompanyCode(companyCode);
        InfoAmount infoAmount = new InfoAmount();
        infoAmount.setSxAmount(BigDecimal.ZERO);
        creditAmount.setResult(JsonUtil.Object2Json(infoAmount));
        certificationBO.saveCertification(creditAmount);
        certifications.add(creditAmount);

        return certifications;
    }

    private InfoBasic getInfoBasic(XN623040Req req) {
        InfoBasic data = new InfoBasic();
        data.setEducation(req.getEducation());
        data.setMarriage(req.getMarriage());
        data.setChildrenNum(StringValidater.toInteger(req.getChildrenNum()));
        data.setProvinceCity(req.getProvinceCity());
        data.setAddress(req.getAddress());
        data.setLiveTime(req.getLiveTime());
        data.setWechat(req.getWechat());
        data.setEmail(req.getEmail());
        return data;
    }

    private InfoOccupation getInfoOccupation(XN623041Req req) {
        InfoOccupation data = new InfoOccupation();
        data.setOccupation(req.getOccupation());
        data.setIncome(req.getIncome());
        data.setCompany(req.getCompany());
        data.setProvinceCity(req.getProvinceCity());
        data.setAddress(req.getAddress());
        data.setPhone(req.getPhone());
        return data;
    }

    private InfoContact getInfoContact(XN623042Req req) {
        InfoContact data = new InfoContact();
        data.setFamilyRelation(req.getFamilyRelation());
        data.setFamilyName(req.getFamilyName());
        data.setFamilyMobile(req.getFamilyMobile());
        data.setSocietyRelation(req.getSocietyRelation());
        data.setSocietyName(req.getSocietyName());
        data.setSocietyMobile(req.getSocietyMobile());
        return data;
    }

    // private InfoBankcard getInfoBankcard(XN623043Req req) {
    // InfoBankcard data = new InfoBankcard();
    // data.setBank(req.getBank());
    // data.setPrivinceCity(req.getPrivinceCity());
    // data.setMobile(req.getMobile());
    // data.setCardNo(req.getCardNo());
    // return data;
    // }

    @Override
    public void doCheckValidDaily() {
        logger.info("***************开始扫描认证结果***************");
        Certification condition = new Certification();
        condition.setFlag(ECertificationStatus.CERTI_YES.getCode());
        condition.setCurDatetime(new Date());
        List<Certification> certificationList = certificationBO
            .queryCertificationList(condition);
        if (CollectionUtils.isNotEmpty(certificationList)) {
            for (Certification certification : certificationList) {
                if (ECertiKey.INFO_BASIC.getCode().equals(
                    certification.getCertiKey())
                        || ECertiKey.INFO_CONTACT.getCode().equals(
                            certification.getCertiKey())
                        || ECertiKey.INFO_OCCUPATION.getCode().equals(
                            certification.getCertiKey())) {
                    Certification personal = certificationBO.getCertification(
                        certification.getUserId(), ECertiKey.INFO_PERSONAL);
                }
                certificationBO.makeInvalid(certification);
                // 如果额度失效，用户还未使用该额度，则将产品重置可申请
                if (ECertiKey.INFO_AMOUNT.getCode().equals(
                    certification.getCertiKey())) {
                    Apply apply = applyBO.getCurrentApply(certification
                        .getUserId());
                    if (apply != null
                            && EApplyStatus.APPROVE_YES.getCode().equals(
                                apply.getStatus())) {
                        apply.setStatus(EApplyStatus.CANCEL.getCode());
                        applyBO.refreshStatus(apply);
                    }
                }
            }
        }
        logger.info("***************结束扫描认证结果***************");
    }

    private String getMxReport(String mobile, String taskId, String companyCode) {
        String report = null;
        String url = sysConfigBO.getStringValue(SysConstants.MX_URL,
            companyCode);
        String token = sysConfigBO.getStringValue(SysConstants.MX_TOKEN,
            companyCode);
        String urlString = String.format(url, mobile, taskId);
        Properties formProperties = new Properties();
        formProperties.put("Authorization", "token " + token);
        try {
            logger.info(urlString);
            report = HttpUtil.requestGetGzip(urlString, null, formProperties);
        } catch (Exception e) {
            logger.error("获取魔蝎报告异常,原因：" + e.getMessage());
        }
        return report;
    }

    public static String requestGet(String urlString, byte[] requestData,
            Properties requestProperties) throws Exception {
        String responseData = null;

        HttpURLConnection con = null;

        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();

            if ((requestProperties != null) && (requestProperties.size() > 0)) {
                for (Map.Entry<Object, Object> entry : requestProperties
                    .entrySet()) {
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    con.setRequestProperty(key, value);
                }
            }
            con.setConnectTimeout(10000);
            con.setRequestMethod("GET"); // 置为GET方法
            con.setDoInput(true); // 开启输入流
            con.setDoOutput(true); // 开启输出流
            // con.setUseCaches(false); // 不使用缓存
            // logger.debug("打开连接:" + url);
            con.connect();
            // 如果请求数据不为空，输出该数据。
            if (requestData != null) {
                DataOutputStream out = new DataOutputStream(
                    con.getOutputStream());
                out.write(requestData);
                out.flush();
                out.close();
            }
            InputStream bStream = con.getInputStream();
            ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
            GZIPInputStream gis = new GZIPInputStream(bStream);
            byte[] buffer = new byte[1];
            int len;

            while ((len = gis.read(buffer)) != -1) {
                bOutStream.write(buffer, 0, len);
            }
            bOutStream.close();
            gis.close();
            responseData = new String(bOutStream.toByteArray());
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                con.disconnect();
                con = null;
            }
        }

        return responseData;
    }

    @Override
    public void setCreditScore(String userId, BigDecimal creditScore) {
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        InfoAmount infoAmount = new InfoAmount();
        infoAmount.setSxAmount(creditScore);
        Integer config = sysConfigBO.getIntegerValue(
            SysConstants.AMOUNT_VALID_DAYS, certification.getCompanyCode());
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(infoAmount));
            certification.setCerDatetime(new Date());
            certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayStart(), config));
            certificationBO.refreshCertification(certification);
        }
    }

    @Override
    public void submitZfb(String userId, String accountNumber, String password) {
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_ZHIFUBAO);
        InfoZfb infoZfb = new InfoZfb();
        infoZfb.setAccountNumber(accountNumber);
        infoZfb.setPassword(password);
        if (null != certification) {
            certification.setCertiKey(ECertiKey.INFO_ZHIFUBAO.getCode());
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(infoZfb));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
        if (certificationBO.isCompleteCerti(userId)) {
            Apply apply = applyBO.getInCertApply(userId);
            if (apply != null) {
                apply.setStatus(EApplyStatus.TO_APPROVE.getCode());
                applyBO.refreshStatus(apply);
            }
        }
    }

    @Override
    public void submitInfoPersonal(String userId) {
        // 获取认证结果
        List<Certification> certifications = certificationBO
            .queryCertificationList(userId);

        if (CollectionUtils.isEmpty(certifications)) {
            throw new BizException("xn623000", "个人认证信息初始化失败");
        }
        Map<String, ECertiKey> keyMap = ECertiKey.getCertiKeyMap();
        for (Certification certification : certifications) {
            if (ECertiKey.INFO_BASIC.getCode().equals(
                certification.getCertiKey())) {
                if (!ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        keyMap.get(ECertiKey.INFO_BASIC.getCode()).getValue()
                                + "未完成认证");
                }
            }
            if (ECertiKey.INFO_CONTACT.getCode().equals(
                certification.getCertiKey())) {
                if (!ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        keyMap.get(ECertiKey.INFO_CONTACT.getCode()).getValue()
                                + "未完成认证");
                }
            }
            if (ECertiKey.INFO_OCCUPATION.getCode().equals(
                certification.getCertiKey())) {
                if (!ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        keyMap.get(ECertiKey.INFO_OCCUPATION.getCode())
                            .getValue() + "未完成认证");
                }
            }
        }
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_PERSONAL);
        certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
        certification.setResult("认证完毕");
        certificationBO.refreshCertification(certification);

        if (certificationBO.isCompleteCerti(userId)) {
            Apply apply = applyBO.getInCertApply(userId);
            if (apply != null) {
                apply.setStatus(EApplyStatus.TO_APPROVE.getCode());
                applyBO.refreshStatus(apply);
            }
        }

    }

    public static void main(String[] args) {
        Map<String, ECertiKey> keyMap = ECertiKey.getCertiKeyMap();
        System.out.println(keyMap.get(ECertiKey.INFO_OCCUPATION.getCode())
            .getValue() + "未完成认证");
    }
}
