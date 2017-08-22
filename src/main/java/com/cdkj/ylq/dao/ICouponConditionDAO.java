package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.CouponCondition;

public interface ICouponConditionDAO extends IBaseDAO<CouponCondition> {
    String NAMESPACE = ICouponConditionDAO.class.getName().concat(".");

    public int updateCount(CouponCondition data);
}
