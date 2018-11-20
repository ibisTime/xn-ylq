package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Way;

//dao层 
public interface IWayDAO extends IBaseDAO<Way> {
    String NAMESPACE = IWayDAO.class.getName().concat(".");

    public int updateWay(Way data);

    public int updatePointCount(Way data);

    public int updateUserCount(Way data);
}
