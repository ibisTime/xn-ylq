package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.BusinessMan;

//dao层 
public interface IBusinessManDAO extends IBaseDAO<BusinessMan> {
    String NAMESPACE = IBusinessManDAO.class.getName().concat(".");

    public int updateMobile(BusinessMan data);

    public int updatePwd(BusinessMan data);

    public int updateStatus(BusinessMan data);

    public int updateRole(BusinessMan data);

    public int updatePhoto(BusinessMan data);

    public int updateCompany(BusinessMan data);
}
