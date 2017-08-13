package com.cdkj.ylq.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.bo.ICertiBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAntifraud;
import com.cdkj.ylq.domain.InfoBankcard;
import com.cdkj.ylq.domain.InfoBasic;
import com.cdkj.ylq.domain.InfoContact;
import com.cdkj.ylq.domain.InfoIdentify;
import com.cdkj.ylq.domain.InfoIdentifyPic;
import com.cdkj.ylq.domain.InfoOccupation;
import com.cdkj.ylq.domain.InfoZMCredit;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN623040Req;
import com.cdkj.ylq.dto.req.XN623041Req;
import com.cdkj.ylq.dto.req.XN623042Req;
import com.cdkj.ylq.dto.req.XN623043Req;
import com.cdkj.ylq.dto.res.XN623050Res;
import com.cdkj.ylq.dto.res.XN798013Res;
import com.cdkj.ylq.dto.res.XN798014Res;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.exception.BizException;

@Service
public class CertificationAOImpl implements ICertificationAO {

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICertiBO certiBO;

    @Override
    public void submitIdentifyPic(String userId, String pic) {
        InfoIdentifyPic data = new InfoIdentifyPic();
        data.setIdentifyPic(pic);
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_IDENTIFY_PIC.getCode());
        if (certification != null) {
            certification.setFlag(EBoolean.YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        } else {
            certification = new Certification();
            certification.setUserId(userId);
            certification.setCertiKey(ECertiKey.INFO_IDENTIFY_PIC.getCode());
            certification.setFlag(EBoolean.YES.getCode());
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
                userId, ECertiKey.INFO_IDENTIFY.getCode());
            if (certification != null) {
                certification.setFlag(EBoolean.YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoIdentify));
                certification.setCerDatetime(new Date());
                certification.setRef("");
                certificationBO.refreshCertification(certification);
            } else {
                certification = new Certification();
                certification.setUserId(userId);
                certification.setCertiKey(ECertiKey.INFO_IDENTIFY.getCode());
                certification.setFlag(EBoolean.YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoIdentify));
                certification.setCerDatetime(new Date());
                certification.setRef("");
                certificationBO.saveCertification(certification);
            }
        }
        return res;
    }

    @Override
    public void submitInfoBasic(XN623040Req req) {
        InfoBasic data = getInfoBasic(req);
        Certification certification = certificationBO.getCertification(
            req.getUserId(), ECertiKey.INFO_BASIC.getCode());
        if (certification != null) {
            certification.setFlag(EBoolean.YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
    }

    @Override
    public void submitInfoOccupation(XN623041Req req) {
        InfoOccupation data = getInfoOccupation(req);
        Certification certification = certificationBO.getCertification(
            req.getUserId(), ECertiKey.INFO_OCCUPATION.getCode());
        if (certification != null) {
            certification.setFlag(EBoolean.YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
    }

    @Override
    public void submitInfoContact(XN623042Req req) {
        InfoContact data = getInfoContact(req);
        Certification certification = certificationBO.getCertification(
            req.getUserId(), ECertiKey.INFO_CONTACT.getCode());
        if (certification != null) {
            certification.setFlag(EBoolean.YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
    }

    @Override
    public void submitInfoBankcard(XN623043Req req) {
        InfoBankcard data = getInfoBankcard(req);
        Certification certification = certificationBO.getCertification(
            req.getUserId(), ECertiKey.INFO_BANKCARD.getCode());
        if (certification != null) {
            certification.setFlag(EBoolean.YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        }
    }

    @Override
    public void submitPersonalInfo(String userId, String ip, String mac,
            String wifiMac, String imei) {
        User user = userBO.getRemoteUser(userId);
        XN623050Res certiInfo = getCertiInfo(userId);
        if (EBoolean.NO.getCode().equals(certiInfo.getIdentifyPicFlag())) {
            throw new BizException("xn623000", "请先在身份认证中上传身份证");
        }
        if (EBoolean.NO.getCode().equals(certiInfo.getIdentifyFlag())) {
            throw new BizException("xn623000", "请先在身份认证中进行人脸识别认证");
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
        if (EBoolean.NO.getCode().equals(certiInfo.getInfoContactFlag())) {
            throw new BizException("xn623000", "请先完成银行卡认证");
        }
        InfoBasic infoBasic = certiInfo.getInfoBasic();
        InfoBankcard infoBankcard = certiInfo.getInfoBankcard();
        InfoIdentify infoIdentify = certiInfo.getInfoIdentify();

        InfoAntifraud infoAntifraud = certiBO.doZhimaCreditAntifraud(
            user.getMobile(), infoIdentify.getIdNo(),
            infoIdentify.getRealName(), infoBankcard.getCardNo(),
            infoBasic.getEmail(),
            infoBasic.getProvinceCity() + infoBasic.getAddress(), ip, mac,
            wifiMac, imei);

        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_ANTIFRAUD.getCode());
        if (certification != null) {
            certification.setFlag(EBoolean.YES.getCode());
            certification.setResult(JsonUtil.Object2Json(infoAntifraud));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.refreshCertification(certification);
        } else {
            certification = new Certification();
            certification.setUserId(userId);
            certification.setCertiKey(ECertiKey.INFO_ANTIFRAUD.getCode());
            certification.setFlag(EBoolean.YES.getCode());
            certification.setResult(JsonUtil.Object2Json(infoAntifraud));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.saveCertification(certification);
        }
    }

    @Override
    public InfoZMCredit doZhimaCreditScoreGet(String userId) {
        User user = userBO.getRemoteUser(userId);
        XN623050Res certiInfo = getCertiInfo(userId);
        if (EBoolean.NO.getCode().equals(certiInfo.getIdentifyPicFlag())) {
            throw new BizException("xn623000", "请先在身份认证中上传身份证");
        }
        if (EBoolean.NO.getCode().equals(certiInfo.getIdentifyFlag())) {
            throw new BizException("xn623000", "请先在身份认证中进行人脸识别认证");
        }
        if (EBoolean.NO.getCode().equals(certiInfo.getAntifraudFlag())) {
            throw new BizException("xn623000", "请先提交基本信息");
        }
        InfoIdentify infoIdentify = certiInfo.getInfoIdentify();
        InfoZMCredit infoZMCredit = certiBO.doZhimaCreditGet(
            user.getSystemCode(), user.getCompanyCode(),
            infoIdentify.getRealName(), infoIdentify.getIdNo());
        if (infoZMCredit.isAuthorized()) {
            Certification certification = certificationBO.getCertification(
                userId, ECertiKey.INFO_ZMCREDIT.getCode());
            if (certification != null) {
                certification.setFlag(EBoolean.YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoZMCredit));
                certification.setCerDatetime(new Date());
                certification.setRef("");
                certificationBO.refreshCertification(certification);
            } else {
                certification = new Certification();
                certification.setUserId(userId);
                certification.setCertiKey(ECertiKey.INFO_ZMCREDIT.getCode());
                certification.setFlag(EBoolean.YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoZMCredit));
                certification.setCerDatetime(new Date());
                certification.setRef("");
                certificationBO.saveCertification(certification);
            }
        }
        return infoZMCredit;
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
        // 查询用户ID是否真实存在
        userBO.getRemoteUser(userId);
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

    private XN623050Res transferCertiInfo(List<Certification> certifications) {
        XN623050Res res = new XN623050Res();
        res.setIdentifyPicFlag(EBoolean.NO.getCode());
        res.setIdentifyFlag(EBoolean.NO.getCode());
        res.setInfoBasicFlag(EBoolean.NO.getCode());
        res.setInfoOccupationFlag(EBoolean.NO.getCode());
        res.setInfoContactFlag(EBoolean.NO.getCode());
        res.setInfoBankcardFlag(EBoolean.NO.getCode());
        res.setAntifraudFlag(EBoolean.NO.getCode());
        res.setZhimaScoreFlag(EBoolean.NO.getCode());
        res.setCarrierFlag(EBoolean.NO.getCode());
        for (Certification certification : certifications) {
            if (ECertiKey.INFO_IDENTIFY.getCode().equals(
                certification.getCertiKey())) {
                res.setIdentifyFlag(certification.getFlag());
                if (EBoolean.YES.getCode().equals(certification.getFlag())) {
                    res.setInfoIdentify(JsonUtil.json2Bean(
                        certification.getResult(), InfoIdentify.class));
                }
            }
            if (ECertiKey.INFO_IDENTIFY_PIC.getCode().equals(
                certification.getCertiKey())) {
                res.setIdentifyPicFlag(certification.getFlag());
                if (EBoolean.YES.getCode().equals(certification.getFlag())) {
                    res.setInfoIdentifyPic(JsonUtil.json2Bean(
                        certification.getResult(), InfoIdentifyPic.class));
                }
            }
            if (ECertiKey.INFO_BASIC.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoBasicFlag(certification.getFlag());
                if (EBoolean.YES.getCode().equals(certification.getFlag())) {
                    res.setInfoBasic(JsonUtil.json2Bean(
                        certification.getResult(), InfoBasic.class));
                }
            }
            if (ECertiKey.INFO_OCCUPATION.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoOccupationFlag(certification.getFlag());
                if (EBoolean.YES.getCode().equals(certification.getFlag())) {
                    res.setInfoOccupation(JsonUtil.json2Bean(
                        certification.getResult(), InfoOccupation.class));
                }
            }
            if (ECertiKey.INFO_CONTACT.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoContactFlag(certification.getFlag());
                if (EBoolean.YES.getCode().equals(certification.getFlag())) {
                    res.setInfoContact(JsonUtil.json2Bean(
                        certification.getResult(), InfoContact.class));
                }
            }
            if (ECertiKey.INFO_BANKCARD.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoBankcardFlag(certification.getFlag());
                if (EBoolean.YES.getCode().equals(certification.getFlag())) {
                    res.setInfoBankcard(JsonUtil.json2Bean(
                        certification.getResult(), InfoBankcard.class));
                }
            }

            if (ECertiKey.INFO_ANTIFRAUD.getCode().equals(
                certification.getCertiKey())) {
                res.setAntifraudFlag(certification.getFlag());
                if (EBoolean.YES.getCode().equals(certification.getFlag())) {
                    res.setInfoAntifraud(JsonUtil.json2Bean(
                        certification.getResult(), InfoAntifraud.class));
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
        identify.setFlag(EBoolean.NO.getCode());
        certificationBO.saveCertification(identify);
        certifications.add(identify);

        // 身份证照片信息
        Certification identifyPic = new Certification();
        identifyPic.setUserId(userId);
        identifyPic.setCertiKey(ECertiKey.INFO_IDENTIFY_PIC.getCode());
        identifyPic.setFlag(EBoolean.NO.getCode());
        certificationBO.saveCertification(identifyPic);
        certifications.add(identifyPic);

        // 基本信息
        Certification certification1 = new Certification();
        certification1.setUserId(userId);
        certification1.setCertiKey(ECertiKey.INFO_BASIC.getCode());
        certification1.setFlag(EBoolean.NO.getCode());
        certificationBO.saveCertification(certification1);
        certifications.add(certification1);

        // 职业信息
        Certification certification2 = new Certification();
        certification2.setUserId(userId);
        certification2.setCertiKey(ECertiKey.INFO_OCCUPATION.getCode());
        certification2.setFlag(EBoolean.NO.getCode());
        certificationBO.saveCertification(certification2);
        certifications.add(certification2);

        // 紧急联系人信息
        Certification certification3 = new Certification();
        certification3.setUserId(userId);
        certification3.setCertiKey(ECertiKey.INFO_CONTACT.getCode());
        certification3.setFlag(EBoolean.NO.getCode());
        certificationBO.saveCertification(certification3);
        certifications.add(certification3);

        // 银行卡信息
        Certification certification4 = new Certification();
        certification4.setUserId(userId);
        certification4.setCertiKey(ECertiKey.INFO_BANKCARD.getCode());
        certification4.setFlag(EBoolean.NO.getCode());
        certificationBO.saveCertification(certification4);
        certifications.add(certification4);

        // 欺诈信息
        Certification antifraud = new Certification();
        antifraud.setUserId(userId);
        antifraud.setCertiKey(ECertiKey.INFO_ANTIFRAUD.getCode());
        antifraud.setFlag(EBoolean.NO.getCode());
        certificationBO.saveCertification(antifraud);
        certifications.add(antifraud);

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
        data.setFamilyMobile(req.getFamilyMobile());
        data.setSocietyRelation(req.getSocietyRelation());
        data.setSocietyMobile(req.getSocietyMobile());
        return data;
    }

    private InfoBankcard getInfoBankcard(XN623043Req req) {
        InfoBankcard data = new InfoBankcard();
        data.setBank(req.getBank());
        data.setPrivinceCity(req.getPrivinceCity());
        data.setMobile(req.getMobile());
        data.setCardNo(req.getCardNo());
        return data;
    }

}
