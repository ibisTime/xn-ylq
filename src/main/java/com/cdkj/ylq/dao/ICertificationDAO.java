package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Certification;

public interface ICertificationDAO extends IBaseDAO<Certification> {
    String NAMESPACE = ICertificationDAO.class.getName().concat(".");

    public int updateCertification(Certification data);
}
