package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.domain.UserCoupon;

public interface IUserCouponBO extends IPaginableBO<UserCoupon> {

    public int saveUserCoupon(String userId, Coupon coupon, String updater,
            String remark);

    public int recycle(UserCoupon data, String updater, String remark);

    public List<UserCoupon> queryUserCouponList(UserCoupon condition);

    public UserCoupon getUserCoupon(Long id);

}
