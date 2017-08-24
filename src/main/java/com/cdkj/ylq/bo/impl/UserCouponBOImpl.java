package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.dao.IUserCouponDAO;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;

@Component
public class UserCouponBOImpl extends PaginableBOImpl<UserCoupon> implements
        IUserCouponBO {

    @Autowired
    private IUserCouponDAO userCouponDAO;

    @Override
    public int saveUserCoupon(String userId, Coupon coupon, String updater,
            String remark) {
        int count = 0;
        if (coupon != null && StringUtils.isNotBlank(userId)) {
            UserCoupon userCoupon = new UserCoupon();
            Date now = new Date();
            userCoupon.setUserId(userId);
            userCoupon.setGetDatetime(now);
            userCoupon.setType(coupon.getType());
            userCoupon.setAmount(coupon.getAmount());
            userCoupon.setStartAmount(coupon.getStartAmount());
            userCoupon.setValidDays(coupon.getValidDays());
            userCoupon.setInvalidDatetime(DateUtil.getRelativeDateOfDays(
                DateUtil.getTodayEnd(), coupon.getValidDays()));
            userCoupon.setStatus(EUserCouponStatus.TO_USE.getCode());
            userCoupon.setUpdater(updater);
            userCoupon.setUpdateDatetime(now);
            userCoupon.setRemark(remark);
            count = userCouponDAO.insert(userCoupon);
        }
        return count;
    }

    @Override
    public int recycle(UserCoupon data, String updater, String remark) {
        int count = 0;
        if (data != null) {
            data.setStatus(EUserCouponStatus.RECYCLE.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userCouponDAO.updateRecycle(data);
        }
        return count;
    }

    @Override
    public List<UserCoupon> queryUserCouponList(UserCoupon condition) {
        return userCouponDAO.selectList(condition);
    }

    @Override
    public UserCoupon getUserCoupon(Long id) {
        UserCoupon data = null;
        if (id != null) {
            UserCoupon condition = new UserCoupon();
            condition.setId(id);
            data = userCouponDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "用户优惠券记录不存在");
            }
        }
        return data;
    }

    @Override
    public UserCoupon getUserCoupon(String borrowCode) {
        UserCoupon data = null;
        if (StringUtils.isNotBlank(borrowCode)) {
            UserCoupon condition = new UserCoupon();
            condition.setBorrowCode(borrowCode);
            data = userCouponDAO.select(condition);
        }
        return data;
    }

    @Override
    public int use(UserCoupon data, String borrowCode) {
        int count = 0;
        if (data != null) {
            data.setBorrowCode(borrowCode);
            data.setStatus(EUserCouponStatus.RECYCLE.getCode());
            userCouponDAO.updateUse(data);
        }
        return count;
    }

    @Override
    public int useCancel(String borrowCode) {
        int count = 0;
        if (StringUtils.isNotBlank(borrowCode)) {
            UserCoupon data = new UserCoupon();
            data.setBorrowCode(borrowCode);
            data.setStatus(EUserCouponStatus.TO_USE.getCode());
            count = userCouponDAO.updateUse(data);
        }
        return count;
    }

    @Override
    public int makeInvalid(UserCoupon userCoupon) {
        userCoupon.setStatus(EUserCouponStatus.INVALID.getCode());
        return userCouponDAO.updateInvalid(userCoupon);
    }

}
