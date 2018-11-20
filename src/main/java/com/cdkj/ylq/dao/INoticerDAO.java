package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Noticer;

//dao层 
public interface INoticerDAO extends IBaseDAO<Noticer> {
    String NAMESPACE = INoticerDAO.class.getName().concat(".");

    public int update(Noticer data);
}
