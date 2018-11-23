package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IBorrowOrderDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.BorrowOrder;

@Repository("borrowOrderDAOImpl")
public class BorrowOrderDAOImpl extends AMybatisTemplate implements
        IBorrowOrderDAO {

    @Override
    public int insert(BorrowOrder data) {
        return super.insert(NAMESPACE.concat("insert_borrow"), data);
    }

    @Override
    public int delete(BorrowOrder data) {
        return 0;
    }

    @Override
    public BorrowOrder select(BorrowOrder condition) {
        return super.select(NAMESPACE.concat("select_borrow"), condition,
            BorrowOrder.class);
    }

    @Override
    public Long selectTotalCount(BorrowOrder condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_borrow_count"),
            condition);
    }

    @Override
    public List<BorrowOrder> selectList(BorrowOrder condition) {
        return super.selectList(NAMESPACE.concat("select_borrow"), condition,
            BorrowOrder.class);
    }

    @Override
    public List<BorrowOrder> selectList(BorrowOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_borrow"), start,
            count, condition, BorrowOrder.class);
    }

    @Override
    public int updateApprove(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_approve"), data);
    }

    @Override
    public int updateLoanSuccess(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_loanSuccess"), data);
    }

    @Override
    public int updateLoanFailure(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_loanFailure"), data);
    }

    @Override
    public int updateResubmitLoan(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_resubmitLoan"), data);
    }

    @Override
    public int updatePayGroup(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int updateRepaySuccess(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_repaySuccess"), data);
    }

    @Override
    public int updateRepayOffline(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_repayOffline"), data);
    }

    @Override
    public int updateConfirmBad(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_confirmBad"), data);
    }

    @Override
    public int updateOverdue(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_overdue"), data);
    }

    @Override
    public int updateArchive(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_archive"), data);
    }

    @Override
    public int updateRemark(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_remark"), data);
    }

    @Override
    public int updateCoupon(BorrowOrder data) {
        return super.update(NAMESPACE.concat("update_coupon"), data);
    }

}
