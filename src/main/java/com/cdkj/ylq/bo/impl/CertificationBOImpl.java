package com.cdkj.ylq.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.dao.ICertificationDAO;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.exception.BizException;

@Component
public class CertificationBOImpl extends PaginableBOImpl<Certification>
        implements ICertificationBO {

    @Autowired
    private ICertificationDAO certificationDAO;

    @Override
    public String saveCertification(Certification data) {
        String code = null;
        if (data != null) {
            certificationDAO.insert(data);
        }
        return code;
    }

    @Override
    public int refreshCertification(Certification data) {
        return certificationDAO.updateCertification(data);
    }

    @Override
    public int refreshFlag(Certification data, ECertificationStatus status) {
        data.setFlag(status.getCode());
        return certificationDAO.updateFlag(data);
    }

    @Override
    public Certification getCertification(Long id) {
        Certification data = null;
        if (id != null) {
            Certification condition = new Certification();
            condition.setId(id);
            data = certificationDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "认证记录不存在");
            }
        }
        return data;
    }

    @Override
    public Certification getCertification(String userId, ECertiKey certiKey) {
        Certification condition = new Certification();
        condition.setUserId(userId);
        condition.setCertiKey(certiKey.getCode());
        return certificationDAO.select(condition);
    }

    @Override
    public List<Certification> queryCertificationList(String userId) {
        Certification condition = new Certification();
        condition.setUserId(userId);
        return certificationDAO.selectList(condition);
    }

    @Override
    public List<Certification> queryCertificationList(Certification condition) {
        return certificationDAO.selectList(condition);
    }

    @Override
    public void makeInvalid(Certification certification) {
        certification.setFlag(ECertificationStatus.INVALID.getCode());
        certificationDAO.updateFlag(certification);
    }

    @Override
    public void resetSxAmount(String userId) {
        Certification certification = this.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        InfoAmount infoAmount = JsonUtil.json2Bean(certification.getResult(),
            InfoAmount.class);
        infoAmount.setSxAmount(0L);
        certification.setResult(JsonUtil.Object2Json(infoAmount));
        certification.setFlag(ECertificationStatus.INVALID.getCode());
        this.refreshCertification(certification);
    }

    @Override
    public void refreshSxAmount(String userId, Long amount) {
        Certification certification = this.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        InfoAmount infoAmount = JsonUtil.json2Bean(certification.getResult(),
            InfoAmount.class);
        infoAmount.setSxAmount(infoAmount.getSxAmount() + amount);
        certification.setResult(JsonUtil.Object2Json(infoAmount));
        this.refreshCertification(certification);
    }

    @Override
    public boolean isCompleteCerti(String userId) {
        boolean flag = false;
        Certification identify = getCertification(userId,
            ECertiKey.INFO_IDENTIFY);
        Certification antifraud = getCertification(userId,
            ECertiKey.INFO_ANTIFRAUD);
        Certification zmcredit = getCertification(userId,
            ECertiKey.INFO_ZMCREDIT);
        Certification carrier = getCertification(userId, ECertiKey.INFO_CARRIER);
        if (identify != null && antifraud != null && zmcredit != null
                && carrier != null) {
            if (EBoolean.YES.getCode().equals(identify.getFlag())
                    && EBoolean.YES.getCode().equals(antifraud.getFlag())
                    && EBoolean.YES.getCode().equals(zmcredit.getFlag())
                    && EBoolean.YES.getCode().equals(carrier.getFlag())) {
                flag = true;
            }
        }
        return flag;
    }

}
