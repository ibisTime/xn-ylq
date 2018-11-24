package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.domain.UserCoupon;

public interface IUserCouponBO extends IPaginableBO<UserCoupon> {

    public int saveUserCoupon(String userId, Coupon coupon, String updater,
            String remark, String companyCode);

    public int recycle(UserCoupon data, String updater, String remark);

    // 使用优惠券
    public int use(UserCoupon data, String borrowCode);

    // 取消借款，返还优惠券
    public int useCancel(String borrowCode);

    // 优惠券失效
    public int makeInvalid(UserCoupon userCoupon);

    public List<UserCoupon> queryUserCouponList(UserCoupon condition);

    public UserCoupon getUserCoupon(Long id);

    public UserCoupon getUserCoupon(String borrowCode);

}
