package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Borrow;

public interface IBorrowDAO extends IBaseDAO<Borrow> {
    String NAMESPACE = IBorrowDAO.class.getName().concat(".");

    public int updateApprove(Borrow data);

    public int updateLoanSuccess(Borrow data);

    public int updateBaofooPaySubmit(Borrow data);

    public int updateBaofooPaySuccess(Borrow data);

    public int updateBaofooPayFailure(Borrow data);

    public int updateLoanFailure(Borrow data);

    public int updateResubmitLoan(Borrow data);

    public int updatePayGroup(Borrow data);

    public int updateRepaySuccess(Borrow data);

    public int updateRepayOffline(Borrow data);

    public int updateRenewalSuccess(Borrow data);

    public int updateRenewalOffline(Borrow data);

    public int updateConfirmBad(Borrow data);

    public int updateOverdue(Borrow data);

    public int updateArchive(Borrow data);

    public int updateRemark(Borrow data);

}
