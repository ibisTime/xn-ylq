package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Statistic;

public interface IStatisticDAO extends IBaseDAO<Statistic> {
    String NAMESPACE = IStatisticDAO.class.getName().concat(".");

    public int doInsertStatisticDaily(Statistic condition);

}
