package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.CertRecord;

//CHECK ��鲢��ע�� 
public interface ICertRecordBO extends IPaginableBO<CertRecord> {

    public boolean isCertRecordExist(Long id);

    public String saveCertRecord(String userId, BigDecimal fee, String certKey,
            String companyCode);

    public List<CertRecord> queryCertRecordList(CertRecord condition);

    public CertRecord getCertRecord(Long id);

}
