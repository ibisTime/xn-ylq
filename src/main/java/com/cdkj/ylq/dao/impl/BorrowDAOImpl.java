package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IBorrowDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Borrow;

@Repository("borrowDAOImpl")
public class BorrowDAOImpl extends AMybatisTemplate implements IBorrowDAO {

    @Override
    public int insert(Borrow data) {
        return super.insert(NAMESPACE.concat("insert_borrow"), data);
    }

    @Override
    public int delete(Borrow data) {
        return 0;
    }

    @Override
    public Borrow select(Borrow condition) {
        return super.select(NAMESPACE.concat("select_borrow"), condition,
            Borrow.class);
    }

    @Override
    public Long selectTotalCount(Borrow condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_borrow_count"),
            condition);
    }

    @Override
    public List<Borrow> selectList(Borrow condition) {
        return super.selectList(NAMESPACE.concat("select_borrow"), condition,
            Borrow.class);
    }

    @Override
    public List<Borrow> selectList(Borrow condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_borrow"), start,
            count, condition, Borrow.class);
    }

    @Override
    public int updateApprove(Borrow data) {
        return super.update(NAMESPACE.concat("update_approve"), data);
    }

    @Override
    public int updateLoanSuccess(Borrow data) {
        return super.update(NAMESPACE.concat("update_loanSuccess"), data);
    }

    @Override
    public int updateLoanFailure(Borrow data) {
        return super.update(NAMESPACE.concat("update_loanFailure"), data);
    }

    @Override
    public int updateBaofooPaySubmit(Borrow data) {
        return super.update(NAMESPACE.concat("update_baofooPaySubmit"), data);
    }

    @Override
    public int updateBaofooPaySuccess(Borrow data) {
        return super.update(NAMESPACE.concat("update_baofooPaySuccess"), data);
    }

    @Override
    public int updateBaofooPayFailure(Borrow data) {
        return super.update(NAMESPACE.concat("update_baofooPayFailure"), data);
    }

    @Override
    public int updateResubmitLoan(Borrow data) {
        return super.update(NAMESPACE.concat("update_resubmitLoan"), data);
    }

    @Override
    public int updatePayGroup(Borrow data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int updateRepaySuccess(Borrow data) {
        return super.update(NAMESPACE.concat("update_repaySuccess"), data);
    }

    @Override
    public int updateRepayOffline(Borrow data) {
        return super.update(NAMESPACE.concat("update_repayOffline"), data);
    }

    @Override
    public int updateRenewalSuccess(Borrow data) {
        return super.update(NAMESPACE.concat("update_renewalSuccess"), data);
    }

    @Override
    public int updateRenewalOffline(Borrow data) {
        return super.update(NAMESPACE.concat("update_renewalOffline"), data);
    }

    @Override
    public int updateConfirmBad(Borrow data) {
        return super.update(NAMESPACE.concat("update_confirmBad"), data);
    }

    @Override
    public int updateOverdue(Borrow data) {
        return super.update(NAMESPACE.concat("update_overdue"), data);
    }

    @Override
    public int updateArchive(Borrow data) {
        return super.update(NAMESPACE.concat("update_archive"), data);
    }

    @Override
    public int updateRemark(Borrow data) {
        return super.update(NAMESPACE.concat("update_remark"), data);
    }

}
