package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.ICouponConditionAO;
import com.cdkj.ylq.ao.IRepayApplyAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IRepayApplyBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.core.CalculationUtil;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.RepayApply;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBorrowStatus;
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
    private ICouponConditionAO couponConditionAO;

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

            Borrow borrow = borrowBO.getBorrow(repayApply.getBorrowCode());
            if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                    && !EBorrowStatus.OVERDUE.getCode().equals(
                        borrow.getStatus())) {
                throw new BizException("xn623000", "关联的借款订单不处于待还款状态");
            }
            borrowBO.repayOffline(borrow, repayApply.getAmount(), approver);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.REPAY);
            // 额度重置为0
            certificationBO.resetSxAmount(borrow.getApplyUser());
            // 发放优惠券
            couponConditionAO.repaySuccess(repayApply.getApplyUser());
            smsContent = "您的" + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款已经成功还款，合同编号为" + borrow.getCode() + "，详情查看请登录APP。";
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

            Borrow borrow = borrowBO.getBorrow(repayApply.getBorrowCode());
            if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                    && !EBorrowStatus.OVERDUE.getCode().equals(
                        borrow.getStatus())) {
                throw new BizException("xn623000", "关联的借款订单不处于待还款状态");
            }
            borrowBO.renewalOffline(borrow, repayApply.getAmount(), approver);
            smsContent = "您的" + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款已经成功续费，合同编号为" + borrow.getCode() + "，详情查看请登录APP。";
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
        return repayApplyBO.getPaginable(start, limit, condition);
    }

    @Override
    public RepayApply getRepayApply(String code) {
        return repayApplyBO.getRepayApply(code);
    }

}
