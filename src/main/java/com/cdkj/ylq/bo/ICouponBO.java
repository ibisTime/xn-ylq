package com.cdkj.ylq.bo;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.enums.ECouponType;

public interface ICouponBO extends IPaginableBO<Coupon> {

    public int refreshCoupon(Coupon data);

    public Coupon getCoupon(String code);

    public Coupon getCoupon(ECouponType type);

    public int refreshStatus(Coupon data, String status, String updater,
            String remark);

}
