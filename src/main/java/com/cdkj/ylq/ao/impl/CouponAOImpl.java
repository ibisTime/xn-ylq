package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.ICouponAO;
import com.cdkj.ylq.bo.ICouponBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.dto.req.XN623100Req;
import com.cdkj.ylq.enums.ECouponStatus;

@Service
public class CouponAOImpl implements ICouponAO {

    @Autowired
    private ICouponBO couponBO;

    @Override
    public int editCoupon(XN623100Req req) {
        Coupon coupon = couponBO.getCoupon(req.getCode());
        coupon.setCondition(StringValidater.toInteger(req.getCondition()));
        coupon.setAmount(StringValidater.toBigDecimal(req.getAmount()));
        coupon.setValidDays(StringValidater.toInteger(req.getValidDays()));
        coupon
            .setStartAmount(StringValidater.toBigDecimal(req.getStartAmount()));
        coupon.setUpdater(req.getUpdater());
        coupon.setRemark(req.getRemark());
        return couponBO.refreshCoupon(coupon);
    }

    @Override
    public int openClose(String code, String updater, String remark) {
        Coupon coupon = couponBO.getCoupon(code);
        ECouponStatus toStatus = null;
        if (ECouponStatus.OPEN.getCode().equals(coupon.getStatus())) {
            toStatus = ECouponStatus.CLOSE;
        } else {
            toStatus = ECouponStatus.OPEN;
        }
        return couponBO.refreshStatus(coupon, toStatus.getCode(), updater,
            remark);
    }

    @Override
    public Paginable<Coupon> queryCouponPage(int start, int limit,
            Coupon condition) {
        return couponBO.getPaginable(start, limit, condition);
    }

    @Override
    public Coupon getCoupon(String code) {
        return couponBO.getCoupon(code);
    }

}
