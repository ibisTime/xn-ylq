package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Company;

//dao层 
public interface ICompanyDAO extends IBaseDAO<Company> {
    String NAMESPACE = ICompanyDAO.class.getName().concat(".");

    int update(Company data);

    int updateInfo(Company data);
}
