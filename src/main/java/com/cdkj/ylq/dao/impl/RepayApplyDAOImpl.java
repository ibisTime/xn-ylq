package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IRepayApplyDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.RepayApply;

@Repository("repayApplyDAOImpl")
public class RepayApplyDAOImpl extends AMybatisTemplate implements
        IRepayApplyDAO {

    @Override
    public int insert(RepayApply data) {
        return super.insert(NAMESPACE.concat("insert_repayApply"), data);
    }

    @Override
    public int delete(RepayApply data) {
        return 0;
    }

    @Override
    public RepayApply select(RepayApply condition) {
        return super.select(NAMESPACE.concat("select_repayApply"), condition,
            RepayApply.class);
    }

    @Override
    public Long selectTotalCount(RepayApply condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_repayApply_count"), condition);
    }

    @Override
    public List<RepayApply> selectList(RepayApply condition) {
        return super.selectList(NAMESPACE.concat("select_repayApply"),
            condition, RepayApply.class);
    }

    @Override
    public List<RepayApply> selectList(RepayApply condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_repayApply"), start,
            count, condition, RepayApply.class);
    }

    @Override
    public int updateApprove(RepayApply data) {
        return super.update(NAMESPACE.concat("update_approve"), data);
    }

}
