package com.cdkj.ylq.ao.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
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
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.ICertiBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAddressBook;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.InfoAntifraud;
import com.cdkj.ylq.domain.InfoBasic;
import com.cdkj.ylq.domain.InfoContact;
import com.cdkj.ylq.domain.InfoIdentify;
import com.cdkj.ylq.domain.InfoIdentifyPic;
import com.cdkj.ylq.domain.InfoOccupation;
import com.cdkj.ylq.domain.InfoZMCredit;
import com.cdkj.ylq.domain.MxCarrierNofification;
import com.cdkj.ylq.domain.MxReportData;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN623040Req;
import com.cdkj.ylq.dto.req.XN623041Req;
import com.cdkj.ylq.dto.req.XN623042Req;
import com.cdkj.ylq.dto.res.XN623050Res;
import com.cdkj.ylq.dto.res.XN798013Res;
import com.cdkj.ylq.dto.res.XN798014Res;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EIDKind;
import com.cdkj.ylq.exception.BizException;

@Service
public class CertificationAOImpl implements ICertificationAO {

    protected static final Logger logger = LoggerFactory
        .getLogger(ICertificationAO.class);

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

    @Override
    public void submitIdentifyPic(String userId, String identifyPic,
            String identifyPicReverse, String identifyPicHand) {
        InfoIdentifyPic data = new InfoIdentifyPic();
        data.setIdentifyPic(identifyPic);
        data.setIdentifyPicReverse(identifyPicReverse);
        data.setIdentifyPicHand(identifyPicHand);
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_IDENTIFY_PIC);
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        } else {
            certification = new Certification();
            certification.setUserId(userId);
            certification.setCertiKey(ECertiKey.INFO_IDENTIFY_PIC.getCode());
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.saveCertification(certification);
        }
    }

    @Override
    public XN798013Res doZhimaVerify(String userId, String idKind, String idNo,
            String realName, String returnUrl, String localCheck, String remark) {
        User user = userBO.getRemoteUser(userId);
        return certiBO.doZhimaVerify(user.getSystemCode(),
            user.getCompanyCode(), userId, idKind, idNo, realName, returnUrl,
            localCheck, remark);
    }

    @Override
    public XN798014Res doZhimaQuery(String userId, String bizNo) {
        User user = userBO.getRemoteUser(userId);
        XN798014Res res = certiBO.doZhimaQuery(user.getSystemCode(),
            user.getCompanyCode(), bizNo);
        if (res.isSuccess()) {
            InfoIdentify infoIdentify = new InfoIdentify();
            infoIdentify.setRealName(res.getRealName());
            infoIdentify.setIdNo(res.getIdNo());
            Certification certification = certificationBO.getCertification(
                userId, ECertiKey.INFO_IDENTIFY_FACE);
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoIdentify));
                certification.setCerDatetime(new Date());
                certification.setRef("");
                certificationBO.refreshCertification(certification);
            } else {
                certification = new Certification();
                certification.setUserId(userId);
                certification.setCertiKey(ECertiKey.INFO_IDENTIFY_FACE
                    .getCode());
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoIdentify));
                certification.setCerDatetime(new Date());
                certification.setRef("");
                certificationBO.saveCertification(certification);
            }
            userBO.doIdentify(userId, EIDKind.IDCard.getCode(),
                infoIdentify.getIdNo(), infoIdentify.getRealName());
        }
        return res;
    }

    @Override
    public void submitIdentifyInfo(String userId) {
        userBO.getRemoteUser(userId);
        XN623050Res certiInfo = getCertiInfo(userId);
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoIdentifyPicFlag())) {
            throw new BizException("xn623000", "请先在上传身份证");
        }
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoIdentifyFaceFlag())) {
            throw new BizException("xn623000", "请先进行人脸识别");
        }
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_IDENTIFY);
        InfoIdentify infoIdentify = certiInfo.getInfoIdentifyFace();
        Integer config = sysConfigBO
            .getIntegerValue(SysConstants.IDENTIFY_VALID_DAYS);
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setCerDatetime(new Date());
            certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayStart(), config));
            certification.setResult(JsonUtil.Object2Json(infoIdentify));
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        } else {
            certification = new Certification();
            certification.setUserId(userId);
            certification.setCertiKey(ECertiKey.INFO_IDENTIFY.getCode());
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(infoIdentify));
            certification.setCerDatetime(new Date());
            certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayStart(), config));
            certification.setRef("");
            certificationBO.saveCertification(certification);
        }

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
        } else {
            certification = new Certification();
            certification.setUserId(req.getUserId());
            certification.setCertiKey(ECertiKey.INFO_BASIC.getCode());
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.saveCertification(certification);
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
        } else {
            certification = new Certification();
            certification.setUserId(req.getUserId());
            certification.setCertiKey(ECertiKey.INFO_OCCUPATION.getCode());
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.saveCertification(certification);
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
        } else {
            certification = new Certification();
            certification.setUserId(req.getUserId());
            certification.setCertiKey(ECertiKey.INFO_CONTACT.getCode());
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.saveCertification(certification);
        }
    }

    // @Override
    // public void submitInfoBankcard(XN623043Req req) {
    // InfoBankcard data = getInfoBankcard(req);
    // Certification certification = certificationBO.getCertification(
    // req.getUserId(), ECertiKey.INFO_BANKCARD);
    // if (certification != null) {
    // certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
    // certification.setResult(JsonUtil.Object2Json(data));
    // certification.setCerDatetime(new Date());
    // certification.setRef("");
    // certificationBO.refreshCertification(certification);
    // } else {
    // certification = new Certification();
    // certification.setUserId(req.getUserId());
    // certification.setCertiKey(ECertiKey.INFO_BANKCARD.getCode());
    // certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
    // certification.setResult(JsonUtil.Object2Json(data));
    // certification.setCerDatetime(new Date());
    // certification.setRef("");
    // certificationBO.saveCertification(certification);
    // }
    // }

    @Override
    public void submitPersonalInfo(String userId, String ip, String mac,
            String wifiMac, String imei) {
        User user = userBO.getRemoteUser(userId);
        XN623050Res certiInfo = getCertiInfo(userId);
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoIdentifyFlag())) {
            throw new BizException("xn623000", "请先进行身份认证");
        }
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoBasicFlag())) {
            throw new BizException("xn623000", "请先完善基本信息");
        }
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoOccupationFlag())) {
            throw new BizException("xn623000", "请先完善职业信息");
        }
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoContactFlag())) {
            throw new BizException("xn623000", "请先完善紧急联系人信息");
        }
        // if (EBoolean.NO.getCode().equals(certiInfo.getInfoBankcardFlag())) {
        // throw new BizException("xn623000", "请先完善银行卡信息");
        // }
        InfoBasic infoBasic = certiInfo.getInfoBasic();
        // InfoBankcard infoBankcard = certiInfo.getInfoBankcard();
        InfoIdentify infoIdentify = certiInfo.getInfoIdentify();

        InfoAntifraud infoAntifraud = certiBO.doZhimaCreditAntifraud(
            user.getSystemCode(), user.getCompanyCode(), user.getMobile(),
            infoIdentify.getIdNo(), infoIdentify.getRealName(), null,
            infoBasic.getEmail(),
            infoBasic.getProvinceCity() + infoBasic.getAddress(), ip, mac,
            wifiMac, imei);

        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_ANTIFRAUD);
        Integer config = sysConfigBO
            .getIntegerValue(SysConstants.ANTIFRAUD_VALID_DAYS);
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(infoAntifraud));
            certification.setCerDatetime(new Date());
            certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayStart(), config));
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        } else {
            certification = new Certification();
            certification.setUserId(userId);
            certification.setCertiKey(ECertiKey.INFO_ANTIFRAUD.getCode());
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(infoAntifraud));
            certification.setCerDatetime(new Date());
            certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayStart(), config));
            certification.setRef("");
            certificationBO.saveCertification(certification);
        }
    }

    @Override
    public InfoZMCredit doZhimaCreditScoreGet(String userId) {
        User user = userBO.getRemoteUser(userId);
        XN623050Res certiInfo = getCertiInfo(userId);
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoIdentifyFlag())) {
            throw new BizException("xn623000", "请先进行身份认证");
        }
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoAntifraudFlag())) {
            throw new BizException("xn623000", "请先提交基本信息");
        }
        InfoIdentify infoIdentify = certiInfo.getInfoIdentify();
        InfoZMCredit infoZMCredit = certiBO.doZhimaCreditGet(
            user.getSystemCode(), user.getCompanyCode(),
            infoIdentify.getRealName(), infoIdentify.getIdNo());
        if (infoZMCredit.isAuthorized()) {
            Certification certification = certificationBO.getCertification(
                userId, ECertiKey.INFO_ZMCREDIT);
            Integer config = sysConfigBO
                .getIntegerValue(SysConstants.ZMSCORE_VALID_DAYS);
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoZMCredit));
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef("");
                certificationBO.refreshCertification(certification);
            } else {
                certification = new Certification();
                certification.setUserId(userId);
                certification.setCertiKey(ECertiKey.INFO_ZMCREDIT.getCode());
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoZMCredit));
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef("");
                certificationBO.saveCertification(certification);
            }
        }
        return infoZMCredit;
    }

    @Override
    @Transactional
    public MxReportData doCarrierVerify(String userId, String taskId) {
        MxReportData mxReportData = certiBO.doMxReportDataGet(taskId);
        if (mxReportData != null) {
            Certification certification = certificationBO.getCertification(
                userId, ECertiKey.INFO_CARRIER);
            Integer config = sysConfigBO
                .getIntegerValue(SysConstants.CARRIER_VALID_DAYS);
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(mxReportData.getReportData());
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef("");
                certificationBO.refreshCertification(certification);
            } else {
                certification = new Certification();
                certification.setUserId(userId);
                certification.setCertiKey(ECertiKey.INFO_CARRIER.getCode());
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(mxReportData.getReportData());
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef("");
                certificationBO.saveCertification(certification);
            }
            Apply apply = applyBO.getCurrentApply(userId);
            if (apply != null) {
                applyBO.toDoApprove(apply);
            }
        }
        return mxReportData;
    }

    @Override
    @Transactional
    public void doMxCarrierCallback(MxCarrierNofification notification) {
        logger.info("&**&*&* 开始魔蝎回调 &*&*&*&*&");
        String userId = notification.getUser_id();
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_CARRIER);
        Integer config = sysConfigBO
            .getIntegerValue(SysConstants.CARRIER_VALID_DAYS);
        // 认证成功
        if (notification.isResult()) {
            String report = getMxReport(notification.getMobile(),
                notification.getTask_id());
            logger.info("&**&*&* 魔蝎报告： &*&*&*&*&" + report);
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(notification));
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef(report);
                certificationBO.refreshCertification(certification);
            } else {
                certification = new Certification();
                certification.setUserId(userId);
                certification.setCertiKey(ECertiKey.INFO_CARRIER.getCode());
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(notification));
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef(report);
                certificationBO.saveCertification(certification);
            }
            Apply apply = applyBO.getCurrentApply(userId);
            if (apply != null) {
                applyBO.toDoApprove(apply);
            }
        }
    }

    @Override
    public void doAddressBookVerify(String userId,
            List<InfoAddressBook> addressBookList) {
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_ADDRESS_BOOK);
        Integer config = sysConfigBO
            .getIntegerValue(SysConstants.ADDRESS_BOOK_VALID_DAYS);
        if (certification != null) {
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(addressBookList));
            certification.setCerDatetime(new Date());
            certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayStart(), config));
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        } else {
            certification = new Certification();
            certification.setUserId(userId);
            certification.setCertiKey(ECertiKey.INFO_ADDRESS_BOOK.getCode());
            certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
            certification.setResult(JsonUtil.Object2Json(addressBookList));
            certification.setCerDatetime(new Date());
            certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayStart(), config));
            certification.setRef("");
            certificationBO.saveCertification(certification);
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
        // 如果查询不到，说明是新用户，初始化认证结果
        if (CollectionUtils.isEmpty(certifications)) {
            certifications = initialCertification(userId);
        }
        // 组装认证结果信息
        XN623050Res res = transferCertiInfo(certifications);
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
        } else {
            infoAmount = new InfoAmount();
            infoAmount.setSxAmount(0L);
            Certification creditAmount = new Certification();
            creditAmount.setUserId(userId);
            creditAmount.setCertiKey(ECertiKey.INFO_AMOUNT.getCode());
            creditAmount.setFlag(ECertificationStatus.TO_CERTI.getCode());
            infoAmount.setSxAmount(0L);
            infoAmount.setFlag(ECertificationStatus.TO_CERTI.getCode());
            creditAmount.setResult(JsonUtil.Object2Json(infoAmount));
            certificationBO.saveCertification(creditAmount);
        }
        return infoAmount;
    }

    private XN623050Res transferCertiInfo(List<Certification> certifications) {
        XN623050Res res = new XN623050Res();
        res.setInfoIdentifyPicFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoIdentifyFaceFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoIdentifyFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoBasicFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoOccupationFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoContactFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoAntifraudFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoZMCreditFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoCarrierFlag(ECertificationStatus.TO_CERTI.getCode());
        res.setInfoAddressBookFlag(ECertificationStatus.TO_CERTI.getCode());

        for (Certification certification : certifications) {
            if (ECertiKey.INFO_IDENTIFY_PIC.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoIdentifyPicFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    res.setInfoIdentifyPic(JsonUtil.json2Bean(
                        certification.getResult(), InfoIdentifyPic.class));
                }
            }
            if (ECertiKey.INFO_IDENTIFY_FACE.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoIdentifyFaceFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())) {
                    res.setInfoIdentifyFace(JsonUtil.json2Bean(
                        certification.getResult(), InfoIdentify.class));
                }
            }
            if (ECertiKey.INFO_IDENTIFY.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoIdentifyFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())
                        || ECertificationStatus.INVALID.getCode().equals(
                            certification.getFlag())) {
                    res.setInfoIdentify(JsonUtil.json2Bean(
                        certification.getResult(), InfoIdentify.class));
                }
            }

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

            if (ECertiKey.INFO_ANTIFRAUD.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoAntifraudFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())
                        || ECertificationStatus.INVALID.getCode().equals(
                            certification.getFlag())) {
                    res.setInfoAntifraud(JsonUtil.json2Bean(
                        certification.getResult(), InfoAntifraud.class));
                }
            }

            if (ECertiKey.INFO_ZMCREDIT.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoZMCreditFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())
                        || ECertificationStatus.INVALID.getCode().equals(
                            certification.getFlag())) {
                    res.setInfoZMCredit(JsonUtil.json2Bean(
                        certification.getResult(), InfoZMCredit.class));
                }
            }

            if (ECertiKey.INFO_CARRIER.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoCarrierFlag(certification.getFlag());
                if (ECertificationStatus.CERTI_YES.getCode().equals(
                    certification.getFlag())
                        || ECertificationStatus.INVALID.getCode().equals(
                            certification.getFlag())) {
                    res.setInfoCarrier(JsonUtil.json2Bean(
                        certification.getResult(), MxCarrierNofification.class));
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
        }
        return res;
    }

    private List<Certification> initialCertification(String userId) {
        List<Certification> certifications = new ArrayList<Certification>();

        // 实名认证信息
        Certification identify = new Certification();
        identify.setUserId(userId);
        identify.setCertiKey(ECertiKey.INFO_IDENTIFY.getCode());
        identify.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(identify);
        certifications.add(identify);

        // 身份证照片信息
        Certification identifyPic = new Certification();
        identifyPic.setUserId(userId);
        identifyPic.setCertiKey(ECertiKey.INFO_IDENTIFY_PIC.getCode());
        identifyPic.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(identifyPic);
        certifications.add(identifyPic);

        // 人脸识别
        Certification identifyFace = new Certification();
        identifyFace.setUserId(userId);
        identifyFace.setCertiKey(ECertiKey.INFO_IDENTIFY_FACE.getCode());
        identifyFace.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(identifyFace);
        certifications.add(identifyFace);

        // 基本信息
        Certification certification1 = new Certification();
        certification1.setUserId(userId);
        certification1.setCertiKey(ECertiKey.INFO_BASIC.getCode());
        certification1.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(certification1);
        certifications.add(certification1);

        // 职业信息
        Certification certification2 = new Certification();
        certification2.setUserId(userId);
        certification2.setCertiKey(ECertiKey.INFO_OCCUPATION.getCode());
        certification2.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(certification2);
        certifications.add(certification2);

        // 紧急联系人信息
        Certification certification3 = new Certification();
        certification3.setUserId(userId);
        certification3.setCertiKey(ECertiKey.INFO_CONTACT.getCode());
        certification3.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(certification3);
        certifications.add(certification3);

        // 欺诈信息
        Certification antifraud = new Certification();
        antifraud.setUserId(userId);
        antifraud.setCertiKey(ECertiKey.INFO_ANTIFRAUD.getCode());
        antifraud.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(antifraud);
        certifications.add(antifraud);

        // 芝麻认证
        Certification zmCredit = new Certification();
        zmCredit.setUserId(userId);
        zmCredit.setCertiKey(ECertiKey.INFO_ZMCREDIT.getCode());
        zmCredit.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(zmCredit);
        certifications.add(zmCredit);

        // 运营商认证
        Certification carrier = new Certification();
        carrier.setUserId(userId);
        carrier.setCertiKey(ECertiKey.INFO_CARRIER.getCode());
        carrier.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(carrier);
        certifications.add(carrier);

        // 通讯录认证
        Certification addressBook = new Certification();
        addressBook.setUserId(userId);
        addressBook.setCertiKey(ECertiKey.INFO_ADDRESS_BOOK.getCode());
        addressBook.setFlag(ECertificationStatus.TO_CERTI.getCode());
        certificationBO.saveCertification(addressBook);
        certifications.add(addressBook);

        // 授信额度
        Certification creditAmount = new Certification();
        creditAmount.setUserId(userId);
        creditAmount.setCertiKey(ECertiKey.INFO_AMOUNT.getCode());
        creditAmount.setFlag(ECertificationStatus.TO_CERTI.getCode());
        InfoAmount infoAmount = new InfoAmount();
        infoAmount.setSxAmount(0L);
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
        data.setQq(req.getQq());
        data.setEmail(req.getEmail());
        return data;
    }

    private InfoOccupation getInfoOccupation(XN623041Req req) {
        InfoOccupation data = new InfoOccupation();
        data.setOccupation(req.getOccupation());
        data.setIncome(StringValidater.toLong(req.getIncome()));
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

    private String getMxReport(String mobile, String taskId) {
        String report = null;
        String url = sysConfigBO.getStringValue(SysConstants.MX_URL);
        String token = sysConfigBO.getStringValue(SysConstants.MX_TOKEN);
        String urlString = String.format(url, mobile, taskId);
        Properties formProperties = new Properties();
        formProperties.put("Authorization", "token " + token);
        try {
            logger.info(urlString);
            report = requestGet(urlString, null, formProperties);
        } catch (Exception e) {
            logger.error("获取魔蝎报告异常");
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
}
