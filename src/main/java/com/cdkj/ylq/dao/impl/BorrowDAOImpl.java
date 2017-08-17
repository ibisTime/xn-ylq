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
    public int updateLoan(Borrow data) {
        return super.update(NAMESPACE.concat("update_loan"), data);
    }

    @Override
    public int updatePayGroup(Borrow data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int updateRepaySuccess(Borrow data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

}
