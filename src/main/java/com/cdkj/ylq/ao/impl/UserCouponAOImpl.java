package com.cdkj.ylq.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IUserCouponAO;
import com.cdkj.ylq.bo.ICouponBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.enums.ECouponStatus;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;

@Service
public class UserCouponAOImpl implements IUserCouponAO {

    protected static final Logger logger = LoggerFactory
        .getLogger(IUserCouponAO.class);

    @Autowired
    private IUserCouponBO userCouponBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICouponBO couponBO;

    @Autowired
    private IProductBO productBO;

    @Override
    public void grant(String userId, String couponCode, String updater,
            String remark) {
        Coupon coupon = couponBO.getCoupon(couponCode);
        if (ECouponStatus.CLOSE.getCode().equals(coupon.getStatus())) {
            throw new BizException("623130", "该优惠活动处于关闭状态，不能发放");
        }
        userCouponBO.saveUserCoupon(userId, coupon, updater, "管理端手动发放");
    }

    @Override
    public void recycle(Long id, String updater, String remark) {
        UserCoupon userCoupon = userCouponBO.getUserCoupon(id);
        if (!EUserCouponStatus.TO_USE.getCode().equals(userCoupon.getStatus())) {
            throw new BizException("623131", "该优惠券不处于可用状态，不能回收");
        }
        userCouponBO.recycle(userCoupon, updater, remark);
    }

    @Override
    public Paginable<UserCoupon> queryUserCouponPage(int start, int limit,
            UserCoupon condition) {
        Paginable<UserCoupon> results = userCouponBO.getPaginable(start, limit,
            condition);
        if (CollectionUtils.isNotEmpty(results.getList())) {
            for (UserCoupon userCoupon : results.getList()) {
                userCoupon
                    .setUser(userBO.getRemoteUser(userCoupon.getUserId()));
            }
        }
        return results;
    }

    @Override
    public Paginable<UserCoupon> queryMyCouponPage(int start, int limit,
            UserCoupon condition) {
        return userCouponBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<UserCoupon> queryCouponList(UserCoupon condition, Long amount) {
        condition.setProductAmount(amount);
        return userCouponBO.queryUserCouponList(condition);
    }

    @Override
    public UserCoupon getUserCoupon(Long id) {
        UserCoupon userCoupon = userCouponBO.getUserCoupon(id);
        userCoupon.setUser(userBO.getRemoteUser(userCoupon.getUserId()));
        return userCoupon;
    }

    @Override
    public void doCheckUserCouponInvalidDaily() {
        logger.info("***************开始扫描优惠券过期***************");
        UserCoupon condition = new UserCoupon();
        condition.setCurDatetime(new Date());
        List<UserCoupon> userCouponList = userCouponBO
            .queryUserCouponList(condition);
        if (CollectionUtils.isNotEmpty(userCouponList)) {
            for (UserCoupon userCoupon : userCouponList) {
                userCouponBO.makeInvalid(userCoupon);
            }
        }
        logger.info("***************结束扫描优惠券过期***************");
    }

}
