package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.enums.EProductLevel;

public interface IBorrowOrderBO extends IPaginableBO<BorrowOrder> {

    public String saveBorrow(BorrowOrder data);

    public List<BorrowOrder> queryBorrowList(BorrowOrder condition);

    public BorrowOrder getBorrow(String code);

    public BorrowOrder getCurrentBorrow(String userId);

    public void doApprove(BorrowOrder borrow, String status, String approver,
            String approveNote);

    public int loanSuccess(BorrowOrder borrow, String updater, String remark);

    public int loanFailure(BorrowOrder borrow, String updater, String remark);

    public int resubmitLoan(BorrowOrder borrow);

    public String addPayGroup(String code);

    public int confirmBad(BorrowOrder data, String updater, String remark);

    public int overdue(BorrowOrder data);

    public int repaySuccess(BorrowOrder borrow, BigDecimal payAmount,
            String payCode, String payType);

    public int repayOffline(BorrowOrder borrow, BigDecimal repayAmount,
            String updater);

    public int archive(BorrowOrder data);

    public List<BorrowOrder> queryBorrowListByPayGroup(String payGroup);

    // 用户当前可借产品等级
    public EProductLevel getUserBorrowLevel(String userId);

    public int getTotalBorrowCount(String userId);

    public int refreshRemark(String code, String remark);

    public void refreshIsCoupon(BorrowOrder borrow);

    public List<BorrowOrder> getCouponOrders(String userId);

    public void refreshStaging(BorrowOrder borrow, Long stageCount,
            Long stageCycle, String updater, String remark);

    public void refreshStageRepay(BorrowOrder order, BigDecimal amount,
            String updater);

}
