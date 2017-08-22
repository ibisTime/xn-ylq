package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.ICouponConditionDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.CouponCondition;

@Repository("couponConditionDAOImpl")
public class CouponConditionDAOImpl extends AMybatisTemplate implements
        ICouponConditionDAO {

    @Override
    public int insert(CouponCondition data) {
        return super.insert(NAMESPACE.concat("insert_couponCondition"), data);
    }

    @Override
    public int delete(CouponCondition data) {
        return super.delete(NAMESPACE.concat("delete_couponCondition"), data);
    }

    @Override
    public CouponCondition select(CouponCondition condition) {
        return super.select(NAMESPACE.concat("select_couponCondition"),
            condition, CouponCondition.class);
    }

    @Override
    public Long selectTotalCount(CouponCondition condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_couponCondition_count"), condition);
    }

    @Override
    public List<CouponCondition> selectList(CouponCondition condition) {
        return super.selectList(NAMESPACE.concat("select_couponCondition"),
            condition, CouponCondition.class);
    }

    @Override
    public List<CouponCondition> selectList(CouponCondition condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_couponCondition"),
            start, count, condition, CouponCondition.class);
    }

    @Override
    public int updateCount(CouponCondition data) {
        return super.update(NAMESPACE.concat("update_count"), data);
    }

}
