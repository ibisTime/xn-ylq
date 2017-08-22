package com.cdkj.ylq.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ICouponConditionBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.ICouponConditionDAO;
import com.cdkj.ylq.domain.CouponCondition;
import com.cdkj.ylq.enums.ECouponType;
import com.cdkj.ylq.exception.BizException;

@Component
public class CouponConditionBOImpl extends PaginableBOImpl<CouponCondition>
        implements ICouponConditionBO {

    @Autowired
    private ICouponConditionDAO couponConditionDAO;

    @Override
    public CouponCondition saveCouponCondition(String userId,
            ECouponType couponType, Integer count) {
        CouponCondition data = new CouponCondition();
        data.setUserId(userId);
        data.setCouponType(couponType.getCode());
        data.setCount(count);
        couponConditionDAO.insert(data);
        return data;
    }

    @Override
    public int refreshCount(CouponCondition data) {
        int count = 0;
        if (data != null) {
            count = couponConditionDAO.updateCount(data);
        }
        return count;
    }

    @Override
    public CouponCondition getCouponCondition(String userId,
            ECouponType couponType) {
        CouponCondition data = null;
        CouponCondition condition = new CouponCondition();
        condition.setUserId(userId);
        condition.setCouponType(couponType.getCode());
        data = couponConditionDAO.select(condition);
        if (data == null) {
            throw new BizException("xn0000", "优惠券赠送条件不存在");
        }
        return data;
    }
}
