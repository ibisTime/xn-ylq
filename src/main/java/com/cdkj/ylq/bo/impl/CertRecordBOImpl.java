package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ICertRecordBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.ICertRecordDAO;
import com.cdkj.ylq.domain.CertRecord;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Component
public class CertRecordBOImpl extends PaginableBOImpl<CertRecord> implements
        ICertRecordBO {

    @Autowired
    private ICertRecordDAO certRecordDAO;

    @Override
    public boolean isCertRecordExist(Long code) {
        CertRecord condition = new CertRecord();
        condition.setId(code);
        if (certRecordDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<CertRecord> queryCertRecordList(CertRecord condition) {
        return certRecordDAO.selectList(condition);
    }

    @Override
    public CertRecord getCertRecord(Long id) {
        CertRecord data = null;
        CertRecord condition = new CertRecord();
        condition.setId(id);
        data = certRecordDAO.select(condition);
        if (data == null) {
            throw new BizException("xn0000", "该记录不存在");
        }
        return data;
    }

    @Override
    public String saveCertRecord(String userId, BigDecimal fee, String certKey,
            String companyCode) {
        CertRecord data = new CertRecord();
        data.setUserId(userId);
        data.setUseDatetime(new Date());
        data.setFee(fee);
        data.setCertKey(certKey);
        data.setCompanyCode(companyCode);
        certRecordDAO.insert(data);
        return certKey;
    }
}
