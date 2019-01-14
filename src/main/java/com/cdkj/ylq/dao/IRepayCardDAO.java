package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.RepayCard;

//dao层 
public interface IRepayCardDAO extends IBaseDAO<RepayCard> {
    String NAMESPACE = IRepayCardDAO.class.getName().concat(".");

    public int updateAmount(RepayCard data);

    public int update(RepayCard data);
}
