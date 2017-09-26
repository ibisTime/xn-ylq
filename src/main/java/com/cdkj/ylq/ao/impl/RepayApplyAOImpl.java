package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.ICouponConditionAO;
import com.cdkj.ylq.ao.IRepayApplyAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IOverdueBO;
import com.cdkj.ylq.bo.IRenewalBO;
import com.cdkj.ylq.bo.IRepayApplyBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.core.CalculationUtil;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Renewal;
import com.cdkj.ylq.domain.RepayApply;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.EOverdueDeal;
import com.cdkj.ylq.enums.EPayType;
import com.cdkj.ylq.enums.ERenewalStatus;
import com.cdkj.ylq.enums.ERepayApplyStatus;
import com.cdkj.ylq.enums.ERepayApplyType;
import com.cdkj.ylq.exception.BizException;

@Service
public class RepayApplyAOImpl implements IRepayApplyAO {

    @Autowired
    private IRepayApplyBO repayApplyBO;

    @Autowired
    private IBorrowBO borrowBO;

    @Autowired
    private IApplyBO applyBO;

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICouponConditionAO couponConditionAO;

    @Autowired
    private IRenewalBO renewalBO;

    @Autowired
    private IOverdueBO overdueBO;

    @Override
    @Transactional
    public void doApprove(String code, String approveResult, String approver,
            String approveNote) {
        RepayApply repayApply = repayApplyBO.getRepayApply(code);
        if (!ERepayApplyStatus.TO_APPROVE.getCode().equals(
            repayApply.getStatus())) {
            throw new BizException("xn623000", "打款申请记录不处于待审核状态");
        }
        if (ERepayApplyType.REPAY.getCode().equals(repayApply.getType())) {
            doApproveRepay(repayApply, approveResult, approver, approveNote);
        } else if (ERepayApplyType.RENEWAL.getCode().equals(
            repayApply.getType())) {
            doApproveRenewal(repayApply, approveResult, approver, approveNote);
        }
    }

    private void doApproveRepay(RepayApply repayApply, String approveResult,
            String approver, String approveNote) {
        String status = null;
        String smsContent = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ERepayApplyStatus.APPROVE_YES.getCode();

            Borrow borrow = borrowBO.getBorrow(repayApply.getRefNo());
            if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                    && !EBorrowStatus.OVERDUE.getCode().equals(
                        borrow.getStatus())) {
                throw new BizException("xn623000", "关联的借款订单不处于待还款状态");
            }
            // 如果是逾期还款，逾期记录落地
            if (borrow.getYqDays() > 0) {
                overdueBO.saveOverdue(borrow.getApplyUser(), borrow.getCode(),
                    borrow.getYqDays(), borrow.getYqlxAmount(),
                    EOverdueDeal.REPAY.getCode());
            }
            // 更新借款订单信息
            borrowBO.repayOffline(borrow, repayApply.getAmount(), approver);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.REPAY);
            // 额度重置为0
            certificationBO.resetSxAmount(borrow.getApplyUser());
            // 发放优惠券
            couponConditionAO.repaySuccess(repayApply.getApplyUser());
            // 发送短信
            smsContent = "您的" + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款（合同编号：" + borrow.getCode() + "）已经成功还款，详情查看请登录APP。";
        } else if (EBoolean.NO.getCode().equals(approveResult)) {
            status = ERepayApplyStatus.APPROVE_NO.getCode();
            smsContent = "您的线下还款申请审核未通过，原因：" + approveNote + "。";
        }
        repayApplyBO.doApprove(repayApply, status, approver, approveNote);
        smsOutBO.sentContent(repayApply.getApplyUser(), smsContent);
    }

    private void doApproveRenewal(RepayApply repayApply, String approveResult,
            String approver, String approveNote) {
        String status = null;
        String smsContent = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ERepayApplyStatus.APPROVE_YES.getCode();

            Renewal renewal = renewalBO.getRenewal(repayApply.getRefNo());
            if (!ERenewalStatus.TO_PAY.getCode().equals(renewal.getStatus())) {
                throw new BizException("xn623000", "续期申请记录不处于待支付状态");
            }

            Borrow borrow = borrowBO.getBorrow(renewal.getBorrowCode());
            if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                    && !EBorrowStatus.OVERDUE.getCode().equals(
                        borrow.getStatus())) {
                throw new BizException("xn623000", "关联的借款订单不处于待还款状态");
            }
            Integer renewalCount = borrow.getRenewalCount();
            // 如果是逾期还款，逾期记录落地
            if (borrow.getYqDays() > 0) {
                overdueBO.saveOverdue(borrow.getApplyUser(), borrow.getCode(),
                    borrow.getYqDays(), borrow.getYqlxAmount(),
                    EOverdueDeal.RENEWAL.getCode());
            }
            // 更新借款订单
            borrowBO.renewalSuccess(borrow, renewal, repayApply.getAmount());
            // 更新续期记录
            renewalBO.renewalSuccess(renewal, "线下", EPayType.OFFLINE.getCode(),
                renewalCount + 1);

            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOANING);
            smsContent = "您的" + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款（合同编号：" + borrow.getCode() + "）已经成功续期，详情查看请登录APP。";
        } else if (EBoolean.NO.getCode().equals(approveResult)) {
            status = ERepayApplyStatus.APPROVE_NO.getCode();
            smsContent = "您的线下续期申请审核未通过，原因：" + approveNote + "。";
        }
        repayApplyBO.doApprove(repayApply, status, approver, approveNote);
        smsOutBO.sentContent(repayApply.getApplyUser(), smsContent);
    }

    @Override
    public Paginable<RepayApply> queryRepayApplyPage(int start, int limit,
            RepayApply condition) {
        Paginable<RepayApply> results = repayApplyBO.getPaginable(start, limit,
            condition);
        for (RepayApply repayApply : results.getList()) {
            repayApply.setUser(userBO.getRemoteUser(repayApply.getApplyUser()));
            if (ERepayApplyType.REPAY.getCode().equals(repayApply.getType())) {
                repayApply.setBorrow(borrowBO.getBorrow(repayApply.getRefNo()));
            } else if (ERepayApplyType.RENEWAL.getCode().equals(
                repayApply.getType())) {
                repayApply.setRenewal(renewalBO.getRenewal(repayApply
                    .getRefNo()));
            }
        }
        return results;
    }

    @Override
    public RepayApply getRepayApply(String code) {
        RepayApply repayApply = repayApplyBO.getRepayApply(code);
        repayApply.setUser(userBO.getRemoteUser(repayApply.getApplyUser()));
        if (ERepayApplyType.REPAY.getCode().equals(repayApply.getType())) {
            repayApply.setBorrow(borrowBO.getBorrow(repayApply.getRefNo()));
        } else if (ERepayApplyType.RENEWAL.getCode().equals(
            repayApply.getType())) {
            repayApply.setRenewal(renewalBO.getRenewal(repayApply.getRefNo()));
        }
        return repayApply;
    }

}
