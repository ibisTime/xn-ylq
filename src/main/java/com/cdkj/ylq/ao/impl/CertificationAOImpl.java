package com.cdkj.ylq.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoBasic;
import com.cdkj.ylq.dto.req.XN623040Req;
import com.cdkj.ylq.dto.res.XN623050Res;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;

@Service
public class CertificationAOImpl implements ICertificationAO {

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String submitBasicInfo(XN623040Req req) {
        InfoBasic data = getInfoBasic(req);
        Certification certification = certificationBO.getCertification(
            req.getUserId(), ECertiKey.INFO_BASIC.getCode());
        if (certification == null) {
            certification = new Certification();
            certification.setUserId(req.getUserId());
            certification.setCertiKey(ECertiKey.INFO_BASIC.getCode());
            certification.setFlag(EBoolean.YES.getCode());
            certification.setResult(JsonUtil.Object2Json(data));
            certification.setCerDatetime(new Date());
            certification.setRef("");
            certificationBO.saveCertification(certification);
        } else {
        }
        return null;
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
        for (Certification certification : certifications) {
            if (ECertiKey.INFO_BASIC.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoBasicFlag(certification.getFlag());
            }
            if (ECertiKey.INFO_OCCUPATION.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoOccupationFlag(certification.getFlag());
            }
            if (ECertiKey.INFO_CONTACT.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoContactFlag(certification.getFlag());
            }
            if (ECertiKey.INFO_BANKCARD.getCode().equals(
                certification.getCertiKey())) {
                res.setInfoBankcardFlag(certification.getFlag());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Boolean.valueOf("true"));
    }

    private List<Certification> initialCertification(String userId) {
        List<Certification> certifications = new ArrayList<Certification>();

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

        return certifications;
    }
}
