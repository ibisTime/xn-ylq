package com.cdkj.ylq.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IBorrowDAO;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Renewal;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.ELoanType;
import com.cdkj.ylq.enums.EPayType;
import com.cdkj.ylq.enums.EProductLevel;
import com.cdkj.ylq.exception.BizException;

@Component
public class BorrowBOImpl extends PaginableBOImpl<Borrow> implements IBorrowBO {

    @Autowired
    private IBorrowDAO borrowDAO;

    @Override
    public String saveBorrow(Borrow data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateM(EGeneratePrefix.BORROW.getCode());
            data.setCode(code);
            borrowDAO.insert(data);
        }
        return code;
    }

    @Override
    public List<Borrow> queryBorrowList(Borrow condition) {
        return borrowDAO.selectList(condition);
    }

    @Override
    public Borrow getBorrow(String code) {
        Borrow data = null;
        if (StringUtils.isNotBlank(code)) {
            Borrow condition = new Borrow();
            condition.setCode(code);
            data = borrowDAO.select(condition);
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
            Borrow data = new Borrow();
            data.setCode(code);
            payGroup = OrderNoGenerater.generateM(EGeneratePrefix.PAY_GROUP
                .getCode());
            data.setPayGroup(payGroup);
            borrowDAO.updatePayGroup(data);
        }
        return payGroup;
    }

    @Override
    public void doApprove(Borrow borrow, String status, String approver,
            String approveNote) {
        Date now = new Date();
        borrow.setStatus(status);
        borrow.setApprover(approver);
        borrow.setApproveDatetime(now);
        borrow.setApproveNote(approveNote);
        borrow.setUpdater(approver);
        borrow.setUpdateDatetime(now);
        borrow.setRemark("已完成审核");
        borrowDAO.updateApprove(borrow);
    }

    @Override
    public int loanSuccess(Borrow borrow, String updater, String remark) {
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
            count = borrowDAO.updateLoanSuccess(borrow);
        }
        return count;
    }

    @Override
    public int loanFailure(Borrow borrow, String updater, String remark) {
        int count = 0;
        if (borrow != null) {
            Date now = new Date();
            borrow.setStatus(EBorrowStatus.LOAN_NO.getCode());
            borrow.setUpdater(updater);
            borrow.setUpdateDatetime(now);
            borrow.setRemark(remark);
            count = borrowDAO.updateLoanSuccess(borrow);
        }
        return count;
    }

    @Override
    public int baofooPaySubmit(Borrow borrow, String updater, String remark) {
        int count = 0;
        if (borrow != null) {
            Date now = new Date();
            borrow.setStatus(EBorrowStatus.PAY_SUBMIT.getCode());
            borrow.setUpdater(updater);
            borrow.setUpdateDatetime(now);
            borrow.setRemark("已申请宝付代付，等待返回结果");
            count = borrowDAO.updateBaofooPaySubmit(borrow);
        }
        return count;
    }

    @Override
    public int baofooPaySuccess(Borrow borrow) {
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
            borrow.setLoanType(ELoanType.BAOFOO.getCode());
            borrow.setStatus(EBorrowStatus.LOANING.getCode());
            borrow.setUpdateDatetime(now);
            borrow.setRemark("宝付代付成功");
            count = borrowDAO.updateLoanSuccess(borrow);
        }
        return count;
    }

    @Override
    public int baofooPayFailure(Borrow borrow, String remark) {
        int count = 0;
        if (borrow != null) {
            Date now = new Date();
            borrow.setStatus(EBorrowStatus.APPROVE_YES.getCode());
            borrow.setUpdateDatetime(now);
            borrow.setRemark("宝付代付失败");
            if (StringUtils.isNotBlank(remark)) {
                borrow.setRemark(borrow.getRemark() + "，原因:" + remark);
            }
            count = borrowDAO.updateBaofooPaySubmit(borrow);
        }
        return count;
    }

    @Override
    public int resubmitLoan(Borrow borrow) {
        int count = 0;
        if (borrow != null) {
            borrow.setStatus(EBorrowStatus.APPROVE_YES.getCode());
            count = borrowDAO.updateResubmitLoan(borrow);
        }
        return count;
    }

    @Override
    public List<Borrow> queryBorrowListByPayGroup(String payGroup) {
        Borrow condition = new Borrow();
        condition.setPayGroup(payGroup);
        return borrowDAO.selectList(condition);
    }

    @Override
    public int repaySuccess(Borrow borrow, Long payAmount, String payCode,
            String payType) {
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
            count = borrowDAO.updateRepaySuccess(borrow);
        }
        return count;
    }

    @Override
    public int repayOffline(Borrow borrow, Long repayAmount, String updater) {
        int count = 0;
        if (borrow != null && StringUtils.isNotBlank(borrow.getCode())) {
            borrow.setRealHkDatetime(new Date());
            borrow.setRealHkAmount(repayAmount);
            borrow.setPayType(EPayType.OFFLINE.getCode());
            borrow.setStatus(EBorrowStatus.REPAY.getCode());
            borrow.setUpdater(updater);
            borrow.setUpdateDatetime(new Date());
            borrow.setRemark("已成功还款");
            count = borrowDAO.updateRepayOffline(borrow);
        }
        return count;
    }

    @Override
    public int renewalSuccess(Borrow borrow, Renewal renewal, Long payAmount) {
        int count = 0;
        if (renewal != null && StringUtils.isNotBlank(renewal.getBorrowCode())) {
            borrow.setTotalAmount(borrow.getTotalAmount()
                    - borrow.getYqlxAmount());
            borrow.setYqDays(0);
            borrow.setYqlxAmount(0L);
            borrow.setJxDatetime(renewal.getStartDate());
            borrow.setHkDatetime(renewal.getEndDate());
            borrow.setRenewalCount(borrow.getRenewalCount() + 1);
            borrow.setStatus(EBorrowStatus.LOANING.getCode());
            borrow.setUpdater(borrow.getApplyUser());
            borrow.setUpdateDatetime(new Date());
            borrow.setRemark("借款续期中");
            count = borrowDAO.updateRenewalSuccess(borrow);
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
    public int confirmBad(Borrow data, String updater, String remark) {
        int count = 0;
        if (data != null) {
            data.setStatus(EBorrowStatus.BAD.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            count = borrowDAO.updateConfirmBad(data);
        }
        return count;
    }

    @Override
    public int overdue(Borrow data) {
        int count = 0;
        if (data != null) {
            count = borrowDAO.updateOverdue(data);
        }
        return count;
    }

    @Override
    public Borrow getCurrentBorrow(String userId) {
        Borrow condition = new Borrow();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.TO_APPROVE.getCode());
        statusList.add(EBorrowStatus.APPROVE_YES.getCode());
        statusList.add(EBorrowStatus.LOANING.getCode());
        statusList.add(EBorrowStatus.LOAN_NO.getCode());
        statusList.add(EBorrowStatus.OVERDUE.getCode());
        condition.setApplyUser(userId);
        condition.setStatusList(statusList);
        return borrowDAO.select(condition);
    }

    @Override
    public EProductLevel getUserBorrowLevel(String userId) {
        EProductLevel level = EProductLevel.ONE;
        Borrow condition = new Borrow();
        condition.setApplyUser(userId);
        condition.setStatus(EBorrowStatus.REPAY.getCode());
        condition.setLevel(EProductLevel.THREE.getCode());
        if (borrowDAO.selectTotalCount(condition) >= 2) {
            level = EProductLevel.FOUR;
        } else {
            condition.setLevel(EProductLevel.TWO.getCode());
            if (borrowDAO.selectTotalCount(condition) >= 2) {
                level = EProductLevel.THREE;
            } else {
                condition.setLevel(EProductLevel.ONE.getCode());
                if (borrowDAO.selectTotalCount(condition) >= 2) {
                    level = EProductLevel.TWO;
                }
            }
        }
        return level;
    }

    @Override
    public int archive(Borrow data) {
        int count = 0;
        count = borrowDAO.updateArchive(data);
        return count;
    }

    @Override
    public int getTotalBorrowCount(String userId) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            Borrow condition = new Borrow();
            condition.setApplyUser(userId);
            List<String> statusList = new ArrayList<String>();
            statusList.add(EBorrowStatus.LOANING.getCode());
            statusList.add(EBorrowStatus.REPAY.getCode());
            statusList.add(EBorrowStatus.OVERDUE.getCode());
            statusList.add(EBorrowStatus.BAD.getCode());
            condition.setStatusList(statusList);
            count = borrowDAO.selectTotalCount(condition).intValue();
        }
        return count;
    }

    @Override
    public int refreshRemark(String code, String remark) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Borrow data = new Borrow();
            data.setCode(code);
            data.setRemark(remark);
            count = borrowDAO.updateRemark(data);
        }
        return count;
    }

}
