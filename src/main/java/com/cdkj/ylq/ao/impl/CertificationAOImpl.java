package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Certification;

@Service
public class CertificationAOImpl implements ICertificationAO {

    @Autowired
    private ICertificationBO certificationBO;

    @Override
    public String addCertification(Certification data) {
        return certificationBO.saveCertification(data);
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
}
