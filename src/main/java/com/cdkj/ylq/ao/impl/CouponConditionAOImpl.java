package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.ICouponConditionAO;
import com.cdkj.ylq.bo.ICouponBO;
import com.cdkj.ylq.bo.ICouponConditionBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.domain.CouponCondition;
import com.cdkj.ylq.enums.ECouponStatus;
import com.cdkj.ylq.enums.ECouponType;

@Service
public class CouponConditionAOImpl implements ICouponConditionAO {

    @Autowired
    private ICouponConditionBO couponConditionBO;

    @Autowired
    private ICouponBO couponBO;

    @Autowired
    private IUserCouponBO userCouponBO;

    @Override
    @Transactional
    public void recommendSuccess(String userId) {
        // 获取以推荐成功次数
        CouponCondition couponCondition = couponConditionBO.getCouponCondition(
            userId, ECouponType.RECOMMENT);
        // 没有新增记录
        if (couponCondition == null) {
            couponCondition = couponConditionBO.saveCouponCondition(userId,
                ECouponType.RECOMMENT, 0);
        }
        couponCondition.setCount(couponCondition.getCount() + 1);
        // 获取优惠券信息
        Coupon coupon = couponBO.getCoupon(ECouponType.RECOMMENT);
        if (coupon != null) {
            // 优惠券状态是OPEN说明这个活动是在进行中的
            if (ECouponStatus.OPEN.getCode().equals(coupon.getStatus())) {
                // 当前该用户已经推荐成功多少人
                Integer condition = coupon.getCondition();
                if (couponCondition.getCount() >= condition) {
                    userCouponBO.saveUserCoupon(userId, coupon, "程序自动发放",
                        "推荐成功赠送");
                    couponCondition.setCount(0); // 重置已推荐人数
                }
            }
        }
        couponConditionBO.refreshCount(couponCondition);
    }

    @Override
    public void repaySuccess(String userId) {
        // 获取以借还成功次数
        CouponCondition couponCondition = couponConditionBO.getCouponCondition(
            userId, ECouponType.BORROW);
        // 没有新增记录
        if (couponCondition == null) {
            couponCondition = couponConditionBO.saveCouponCondition(userId,
                ECouponType.BORROW, 0);
        }
        couponCondition.setCount(couponCondition.getCount() + 1);
        // 获取优惠券信息
        Coupon coupon = couponBO.getCoupon(ECouponType.BORROW);
        if (coupon != null) {
            // 优惠券状态是OPEN说明这个活动是在进行中的
            if (ECouponStatus.OPEN.getCode().equals(coupon.getStatus())) {
                // 当前该用户已经推荐成功多少人
                Integer condition = coupon.getCondition();
                if (couponCondition.getCount() >= condition) {
                    userCouponBO.saveUserCoupon(userId, coupon, "程序自动发放",
                        "借还成功赠送");
                    couponCondition.setCount(0); // 重置已推荐人数
                }
            }
        }
        couponConditionBO.refreshCount(couponCondition);
    }

}
