package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
    public Certification getCarrierCertificationByTaskId(String taskId) {
        Certification condition = new Certification();
        condition.setFlag(ECertificationStatus.CERTING.getCode());
        condition.setResult(taskId);
        condition.setCertiKey(ECertiKey.INFO_CARRIER.getCode());
        List<Certification> resultList = certificationDAO.selectList(condition);
        if (CollectionUtils.isEmpty(resultList)) {
            throw new BizException("xn623000", "找不到taskId=" + taskId
                    + "的运营商认证记录");
        }
        return resultList.get(0);
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
        infoAmount.setSxAmount(BigDecimal.ZERO);
        certification.setResult(JsonUtil.Object2Json(infoAmount));
        certification.setFlag(ECertificationStatus.INVALID.getCode());
        this.refreshCertification(certification);
    }

    @Override
    public void refreshSxAmount(String userId, BigDecimal amount) {
        Certification certification = this.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        InfoAmount infoAmount = JsonUtil.json2Bean(certification.getResult(),
            InfoAmount.class);
        infoAmount.setSxAmount(infoAmount.getSxAmount().add(amount));
        certification.setResult(JsonUtil.Object2Json(infoAmount));
        this.refreshCertification(certification);
    }

    @Override
    public boolean isCompleteCerti(String userId) {
        boolean flag = false;
        Certification ZQZN = getCertification(userId, ECertiKey.INFO_ZQZN);
        Certification zhifubao = getCertification(userId,
            ECertiKey.INFO_ZHIFUBAO);
        Certification carrier = getCertification(userId, ECertiKey.INFO_CARRIER);
        Certification personal = getCertification(userId,
            ECertiKey.INFO_PERSONAL);
        if (ZQZN != null && zhifubao != null && personal != null
                && carrier != null) {
            if (EBoolean.YES.getCode().equals(ZQZN.getFlag())
                    && EBoolean.YES.getCode().equals(zhifubao.getFlag())
                    && EBoolean.YES.getCode().equals(personal.getFlag())
                    && EBoolean.YES.getCode().equals(carrier.getFlag())) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<Certification> queryCertiedList(String userId) {
        Map<String, ECertiKey> keyMap = ECertiKey.getCertiKeyMap();
        Certification condition = new Certification();
        condition.setFlag(EBoolean.YES.getCode());
        condition.setUserId(userId);
        List<Certification> dataList = certificationDAO.selectList(condition);
        for (Certification certification : dataList) {
            certification.setName(keyMap.get(certification.getCertiKey())
                .getValue());
        }
        return dataList;
    }

}
