package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.UserCoupon;

public interface IUserCouponAO {
    static final String DEFAULT_ORDER_COLUMN = "get_datetime";

    // 发放优惠券
    public void grant(String userId, String couponCode, String updater,
            String remark);

    // 回收优惠券
    public void recycle(Long id, String updater, String remark);

    public Paginable<UserCoupon> queryUserCouponPage(int start, int limit,
            UserCoupon condition);

    public List<UserCoupon> queryUserCouponList(UserCoupon condition);

    public UserCoupon getUserCoupon(Long id);

}
