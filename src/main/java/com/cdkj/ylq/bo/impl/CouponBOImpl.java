package com.cdkj.ylq.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ICouponBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.ICouponDAO;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.exception.BizException;

@Component
public class CouponBOImpl extends PaginableBOImpl<Coupon> implements ICouponBO {

    @Autowired
    private ICouponDAO couponDAO;

    @Override
    public int refreshCoupon(Coupon data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = couponDAO.updateCoupon(data);
        }
        return count;
    }

    @Override
    public int refreshStatus(Coupon data, String status, String updater,
            String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            data.setStatus(status);
            data.setUpdater(updater);
            data.setRemark(remark);
            count = couponDAO.updateStatus(data);
        }
        return count;
    }

    @Override
    public Coupon getCoupon(String code) {
        Coupon data = null;
        if (StringUtils.isNotBlank(code)) {
            Coupon condition = new Coupon();
            condition.setCode(code);
            data = couponDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "优惠券编号不存在");
            }
        }
        return data;
    }

}
