package com.cdkj.ylq.bo;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.CouponCondition;
import com.cdkj.ylq.enums.ECouponType;

public interface ICouponConditionBO extends IPaginableBO<CouponCondition> {

    public CouponCondition saveCouponCondition(String userId,
            ECouponType couponType, Integer count);

    public int refreshCount(CouponCondition data);

    public CouponCondition getCouponCondition(String userId,
            ECouponType couponType);

}
