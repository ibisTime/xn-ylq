package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IUserCouponDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.UserCoupon;

@Repository("userCouponDAOImpl")
public class UserCouponDAOImpl extends AMybatisTemplate implements
        IUserCouponDAO {

    @Override
    public int insert(UserCoupon data) {
        return super.insert(NAMESPACE.concat("insert_userCoupon"), data);
    }

    @Override
    public int delete(UserCoupon data) {
        return 0;
    }

    @Override
    public UserCoupon select(UserCoupon condition) {
        return super.select(NAMESPACE.concat("select_userCoupon"), condition,
            UserCoupon.class);
    }

    @Override
    public Long selectTotalCount(UserCoupon condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_userCoupon_count"), condition);
    }

    @Override
    public List<UserCoupon> selectList(UserCoupon condition) {
        return super.selectList(NAMESPACE.concat("select_userCoupon"),
            condition, UserCoupon.class);
    }

    @Override
    public List<UserCoupon> selectList(UserCoupon condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_userCoupon"), start,
            count, condition, UserCoupon.class);
    }

    @Override
    public int updateRecycle(UserCoupon data) {
        return super.update(NAMESPACE.concat("update_recycle"), data);
    }

    @Override
    public int updateUse(UserCoupon data) {
        return super.update(NAMESPACE.concat("update_use"), data);
    }

    @Override
    public int updateInvalid(UserCoupon data) {
        return super.update(NAMESPACE.concat("update_invalid"), data);
    }

}
