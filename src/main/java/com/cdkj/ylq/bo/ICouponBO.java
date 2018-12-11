package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.enums.ECouponType;

public interface ICouponBO extends IPaginableBO<Coupon> {

    public int refreshCoupon(Coupon data);

    public Coupon getCoupon(String code);

    public Coupon getCoupon(ECouponType type, String companyCode);

    public int refreshStatus(Coupon data, String status, String updater,
            String remark);

    public List<Coupon> queryModelCoupons();

    public void saveCoupon(Coupon data);

}
