package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.CNavigate;

public interface ICNavigateDAO extends IBaseDAO<CNavigate> {
    String NAMESPACE = ICNavigateDAO.class.getName().concat(".");

    public int update(CNavigate data);

}
