package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Staging;

//dao层 
public interface IStagingDAO extends IBaseDAO<Staging> {
    String NAMESPACE = IStagingDAO.class.getName().concat(".");

    public int updateRepay(Staging data);

    public int updateStatus(Staging data);
}
