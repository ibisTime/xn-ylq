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

    // 客户端：我的优惠券查询
    public Paginable<UserCoupon> queryMyCouponPage(int start, int limit,
            UserCoupon condition);

    // 客户端：可使用的优惠券查询
    public List<UserCoupon> queryCouponList(UserCoupon condition, Long amount);

    // oss：用户优惠券分页查询
    public Paginable<UserCoupon> queryUserCouponPage(int start, int limit,
            UserCoupon condition);

    // oss：用户优惠券详情查询
    public UserCoupon getUserCoupon(Long id);

    // 定时器调用：每日00:00:00点检查优惠券过期情况
    public void doCheckUserCouponInvalidDaily();

}
