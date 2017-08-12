package com.cdkj.ylq.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.ICertificationDAO;
import com.cdkj.ylq.domain.Certification;
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
}
