package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.dto.req.XN623100Req;

public interface ICouponAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public int editCoupon(XN623100Req req);

    public int openClose(String code, String updater, String remark);

    public Paginable<Coupon> queryCouponPage(int start, int limit,
            Coupon condition);

    public Coupon getCoupon(String code);

}
