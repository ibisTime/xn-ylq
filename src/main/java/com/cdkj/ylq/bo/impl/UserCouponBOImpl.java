package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.IUserCouponDAO;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;

@Component
public class UserCouponBOImpl extends PaginableBOImpl<UserCoupon> implements
        IUserCouponBO {

    @Autowired
    private IUserCouponDAO userCouponDAO;

    @Override
    public int saveUserCoupon(UserCoupon data) {
        int count = 0;
        if (data != null) {
            count = userCouponDAO.insert(data);
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

}
