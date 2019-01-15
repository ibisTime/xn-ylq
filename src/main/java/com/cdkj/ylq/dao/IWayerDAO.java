package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Wayer;

//dao层 
public interface IWayerDAO extends IBaseDAO<Wayer> {
    String NAMESPACE = IWayerDAO.class.getName().concat(".");

    public int updatePwd(Wayer data);

    public int updateStatus(Wayer data);

    public int updateUrlCount(Wayer data);

    public int updateUserCount(Wayer data);
}
