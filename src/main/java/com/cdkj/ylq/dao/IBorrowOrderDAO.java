package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.BorrowOrder;

public interface IBorrowOrderDAO extends IBaseDAO<BorrowOrder> {
    String NAMESPACE = IBorrowOrderDAO.class.getName().concat(".");

    public int updateApprove(BorrowOrder data);

    public int updateLoanSuccess(BorrowOrder data);

    public int updateLoanFailure(BorrowOrder data);

    public int updateResubmitLoan(BorrowOrder data);

    public int updatePayGroup(BorrowOrder data);

    public int updateRepaySuccess(BorrowOrder data);

    public int updateRepayOffline(BorrowOrder data);

    public int updateConfirmBad(BorrowOrder data);

    public int updateOverdue(BorrowOrder data);

    public int updateArchive(BorrowOrder data);

    public int updateRemark(BorrowOrder data);

    public int updateCoupon(BorrowOrder data);

    public int updateStage(BorrowOrder data);

    public int updateRepayStage(BorrowOrder data);

}
