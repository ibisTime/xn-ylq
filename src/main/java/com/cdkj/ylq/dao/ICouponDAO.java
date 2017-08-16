package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Coupon;

public interface ICouponDAO extends IBaseDAO<Coupon> {
    String NAMESPACE = ICouponDAO.class.getName().concat(".");

    public int updateCoupon(Coupon data);

    public int updateStatus(Coupon data);

}
