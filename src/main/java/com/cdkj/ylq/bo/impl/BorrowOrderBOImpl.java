package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IBorrowOrderBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IBorrowOrderDAO;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.ELoanType;
import com.cdkj.ylq.enums.EPayType;
import com.cdkj.ylq.enums.EProductLevel;
import com.cdkj.ylq.exception.BizException;

@Component
public class BorrowOrderBOImpl extends PaginableBOImpl<BorrowOrder> implements
        IBorrowOrderBO {

    @Autowired
    private IBorrowOrderDAO borrowOrderDAO;

    @Override
    public String saveBorrow(BorrowOrder data) {
        String code = null;
        if (data != null) {
            borrowOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public List<BorrowOrder> queryBorrowList(BorrowOrder condition) {
        return borrowOrderDAO.selectList(condition);
    }

    @Override
    public BorrowOrder getBorrow(String code) {
        BorrowOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            BorrowOrder condition = new BorrowOrder();
            condition.setCode(code);
            data = borrowOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "借款编号不存在");
            }
        }
        return data;
    }

    @Override
    public String addPayGroup(String code) {
        String payGroup = null;
        if (StringUtils.isNotBlank(code)) {
            BorrowOrder data = new BorrowOrder();
            data.setCode(code);
            payGroup = OrderNoGenerater.generateM(EGeneratePrefix.PAY_GROUP
                .getCode());
            data.setPayGroup(payGroup);
            borrowOrderDAO.updatePayGroup(data);
        }
        return payGroup;
    }

    @Override
    public void doApprove(BorrowOrder borrow, String status, String approver,
            String approveNote) {
        Date now = new Date();
        borrow.setStatus(status);
        borrow.setUpdater(approver);
        borrow.setUpdateDatetime(now);
        borrow.setRemark("已完成审核");
        borrowOrderDAO.updateApprove(borrow);
    }

    @Override
    public int loanSuccess(BorrowOrder borrow, String updater, String remark) {
        int count = 0;
        if (borrow != null) {
            Date now = new Date();
            Date fkDatetime = now;
            Date jxDatetime = DateUtil.getTomorrowStart(fkDatetime);
            Date hkDatetime = DateUtil.getRelativeDate(jxDatetime,
                borrow.getDuration() * 24 * 3600 - 1);
            borrow.setFkDatetime(fkDatetime);
            borrow.setJxDatetime(jxDatetime);
            borrow.setHkDatetime(hkDatetime);
            borrow.setLoanType(ELoanType.OFFLINE.getCode());
            borrow.setStatus(EBorrowStatus.LOANING.getCode());
            borrow.setUpdater(updater);
            borrow.setUpdateDatetime(now);
            borrow.setRemark(remark);
            count = borrowOrderDAO.updateLoanSuccess(borrow);
        }
        return count;
    }

    @Override
    public int loanFailure(BorrowOrder borrow, String updater, String remark) {
        int count = 0;
        if (borrow != null) {
            Date now = new Date();
            borrow.setStatus(EBorrowStatus.LOAN_NO.getCode());
            borrow.setUpdater(updater);
            borrow.setUpdateDatetime(now);
            borrow.setRemark(remark);
            count = borrowOrderDAO.updateLoanSuccess(borrow);
        }
        return count;
    }

    @Override
    public int resubmitLoan(BorrowOrder borrow) {
        int count = 0;
        if (borrow != null) {
            borrow.setStatus(EBorrowStatus.TO_APPROVE.getCode());
            count = borrowOrderDAO.updateResubmitLoan(borrow);
        }
        return count;
    }

    @Override
    public List<BorrowOrder> queryBorrowListByPayGroup(String payGroup) {
        BorrowOrder condition = new BorrowOrder();
        condition.setPayGroup(payGroup);
        return borrowOrderDAO.selectList(condition);
    }

    @Override
    public int repaySuccess(BorrowOrder borrow, BigDecimal payAmount,
            String payCode, String payType) {
        int count = 0;
        if (borrow != null && StringUtils.isNotBlank(borrow.getCode())) {
            Date now = new Date();
            borrow.setRealHkDatetime(now);
            borrow.setRealHkAmount(payAmount);
            borrow.setPayCode(payCode);
            borrow.setPayType(payType);
            borrow.setStatus(EBorrowStatus.REPAY.getCode());
            borrow.setUpdater(borrow.getApplyUser());
            borrow.setUpdateDatetime(now);
            borrow.setRemark("已成功还款");
            count = borrowOrderDAO.updateRepaySuccess(borrow);
        }
        return count;
    }

    @Override
    public int repayOffline(BorrowOrder borrow, BigDecimal repayAmount,
            String updater) {
        int count = 0;
        if (borrow != null && StringUtils.isNotBlank(borrow.getCode())) {
            borrow.setRealHkDatetime(new Date());
            borrow.setRealHkAmount(borrow.getRealHkAmount().add(repayAmount));
            borrow.setPayType(EPayType.OFFLINE.getCode());
            borrow.setStatus(EBorrowStatus.REPAY.getCode());
            borrow.setIsStage(EBoolean.NO.getCode());
            borrow.setUpdater(updater);
            borrow.setUpdateDatetime(new Date());
            borrow.setRemark("已成功还款");
            count = borrowOrderDAO.updateRepayOffline(borrow);
        }
        return count;
    }

    // @Override
    // public int renewalOffline(Borrow borrow, Renewal renewal, Long amount,
    // String updater) {
    // int count = 0;
    // if (borrow != null && StringUtils.isNotBlank(borrow.getCode())) {
    // borrow.setTotalAmount(borrow.getTotalAmount()
    // - borrow.getYqlxAmount());
    // borrow.setYqDays(0);
    // borrow.setYqlxAmount(0L);
    // borrow.setJxDatetime(renewal.getStartDate());
    // borrow.setHkDatetime(renewal.getEndDate());
    // borrow.setRenewalCount(borrow.getRenewalCount() + 1);
    // borrow.setStatus(EBorrowStatus.LOANING.getCode());
    // borrow.setUpdater(updater);
    // borrow.setUpdateDatetime(new Date());
    // borrow.setRemark("借款续期中");
    // count = borrowDAO.updateRenewalOffline(borrow);
    // }
    // return count;
    // }

    @Override
    public int confirmBad(BorrowOrder data, String updater, String remark) {
        int count = 0;
        if (data != null) {
            data.setStatus(EBorrowStatus.BAD.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            count = borrowOrderDAO.updateConfirmBad(data);
        }
        return count;
    }

    @Override
    public int overdue(BorrowOrder data) {
        int count = 0;
        if (data != null) {
            count = borrowOrderDAO.updateOverdue(data);
        }
        return count;
    }

    @Override
    public BorrowOrder getCurrentBorrow(String userId) {
        BorrowOrder condition = new BorrowOrder();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.TO_APPROVE.getCode());
        statusList.add(EBorrowStatus.APPROVE_YES.getCode());
        statusList.add(EBorrowStatus.LOANING.getCode());
        statusList.add(EBorrowStatus.LOAN_NO.getCode());
        statusList.add(EBorrowStatus.OVERDUE.getCode());
        condition.setApplyUser(userId);
        condition.setStatusList(statusList);
        return borrowOrderDAO.select(condition);
    }

    @Override
    public EProductLevel getUserBorrowLevel(String userId) {
        EProductLevel level = EProductLevel.ONE;
        BorrowOrder condition = new BorrowOrder();
        condition.setApplyUser(userId);
        condition.setStatus(EBorrowStatus.REPAY.getCode());
        condition.setLevel(EProductLevel.THREE.getCode());
        if (borrowOrderDAO.selectTotalCount(condition) >= 2) {
            level = EProductLevel.FOUR;
        } else {
            condition.setLevel(EProductLevel.TWO.getCode());
            if (borrowOrderDAO.selectTotalCount(condition) >= 2) {
                level = EProductLevel.THREE;
            } else {
                condition.setLevel(EProductLevel.ONE.getCode());
                if (borrowOrderDAO.selectTotalCount(condition) >= 2) {
                    level = EProductLevel.TWO;
                }
            }
        }
        return level;
    }

    @Override
    public int archive(BorrowOrder data) {
        int count = 0;
        count = borrowOrderDAO.updateArchive(data);
        return count;
    }

    @Override
    public int getTotalBorrowCount(String userId) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            BorrowOrder condition = new BorrowOrder();
            condition.setApplyUser(userId);
            List<String> statusList = new ArrayList<String>();
            statusList.add(EBorrowStatus.LOANING.getCode());
            statusList.add(EBorrowStatus.REPAY.getCode());
            statusList.add(EBorrowStatus.OVERDUE.getCode());
            statusList.add(EBorrowStatus.BAD.getCode());
            condition.setStatusList(statusList);
            count = borrowOrderDAO.selectTotalCount(condition).intValue();
        }
        return count;
    }

    @Override
    public int refreshRemark(String code, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            BorrowOrder data = new BorrowOrder();
            data.setCode(code);
            data.setRemark(remark);
            count = borrowOrderDAO.updateRemark(data);
        }
        return count;
    }

    @Override
    public void refreshIsCoupon(BorrowOrder borrow) {
        borrow.setIsCoupon(EBoolean.YES.getCode());
        borrowOrderDAO.updateCoupon(borrow);
    }

    @Override
    public List<BorrowOrder> getCouponOrders(String userId) {
        BorrowOrder condition = new BorrowOrder();
        condition.setApplyUser(userId);
        condition.setStatus(EBorrowStatus.REPAY.getCode());
        List<BorrowOrder> lists = borrowOrderDAO.selectList(condition);
        return lists;
    }

    @Override
    public void refreshStaging(BorrowOrder borrow, Long stageCount,
            Long stageCycle, String updater, String remark) {
        borrow.setYqDays(0);
        borrow.setYqlxAmount(BigDecimal.ZERO);
        borrow.setAmount(borrow.getTotalAmount());
        borrow.setIsStage(EBoolean.YES.getCode());
        borrow.setStatus(EBorrowStatus.LOANING.getCode());
        borrow.setStageBatch(borrow.getStageBatch() + 1);
        borrow.setStageCount(stageCount);
        borrow.setStageCycle(stageCycle);
        borrow.setRepayCount(Long.valueOf(0));
        borrow.setUpdater(updater);
        borrow.setUpdateDatetime(new Date());
        borrow.setRemark(remark);
        borrowOrderDAO.updateStage(borrow);
    }

    @Override
    public void refreshStageRepay(BorrowOrder order, BigDecimal amount,
            String updater) {
        order.setTotalAmount(order.getTotalAmount().subtract(amount));
        order.setPayType(EPayType.OFFLINE.getCode());
        order.setRepayCount(order.getRepayCount() + 1);
        order.setUpdater(updater);
        order.setUpdateDatetime(new Date());
        order.setRemark("分期还款");
        borrowOrderDAO.updateRepayStage(order);
    }

}
