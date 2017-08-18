package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Borrow;

public interface IBorrowDAO extends IBaseDAO<Borrow> {
    String NAMESPACE = IBorrowDAO.class.getName().concat(".");

    public int updateLoan(Borrow data);

    public int updatePayGroup(Borrow data);

    public int updateRepaySuccess(Borrow data);

    public int updateConfirmBad(Borrow data);

    public int updateOverdue(Borrow data);
}
