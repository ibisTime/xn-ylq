package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.ylq.enums.*;
import com.cdkj.ylq.exception.EBizErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IRepayApplyAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBorrowOrderBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.ICouponBO;
import com.cdkj.ylq.bo.IOverdueBO;
import com.cdkj.ylq.bo.IRepayApplyBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IStagingBO;
import com.cdkj.ylq.bo.IStagingRuleBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.core.CalculationUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.domain.RepayApply;
import com.cdkj.ylq.domain.Staging;
import com.cdkj.ylq.domain.StagingRule;
import com.cdkj.ylq.exception.BizException;

@Service
public class RepayApplyAOImpl implements IRepayApplyAO {

    @Autowired
    private IRepayApplyBO repayApplyBO;

    @Autowired
    private IBorrowOrderBO borrowOrderBO;

    @Autowired
    private IApplyBO applyBO;

    @Autowired
    private IStagingRuleBO stagingRuleBO;

    @Autowired
    private IUserCouponBO userCouponBO;

    @Autowired
    private ICouponBO couponBO;

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOverdueBO overdueBO;

    @Autowired
    private IStagingBO stagingBO;

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
        } else if (ERepayApplyType.STAGE.getCode().equals(repayApply.getType())) {
            doApproveStage(repayApply, approveResult, approver, approveNote);
        }
    }

    private void doApproveRepay(RepayApply repayApply, String approveResult,
            String approver, String approveNote) {
        String status = null;
        String smsContent = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ERepayApplyStatus.APPROVE_YES.getCode();

            BorrowOrder borrow = borrowOrderBO.getBorrow(repayApply.getRefNo());
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
            borrowOrderBO
                .repayOffline(borrow, repayApply.getAmount(), approver);
            // // 额度重置为0
            // certificationBO.resetSxAmount(borrow.getApplyUser());
            // 发放优惠券
            List<BorrowOrder> orders = borrowOrderBO.getCouponOrders(borrow
                .getApplyUser());
            Coupon rule = couponBO.getCoupon(ECouponType.BORROW,
                borrow.getCompanyCode());
            if (ECouponStatus.OPEN.getCode().equals(rule.getStatus())
                    && rule.getCondition() - orders.size() == 1) {
                userCouponBO.saveUserCoupon(borrow.getApplyUser(), rule,
                    "程序自动发放", "成功借还" + rule.getCondition().toString()
                            + "次系统发放优惠券", rule.getCompanyCode());
                for (BorrowOrder borrowOrder : orders) {
                    borrowOrderBO.refreshIsCoupon(borrowOrder);
                }
                borrowOrderBO.refreshIsCoupon(borrow);
            }
            // 发送短信
            smsContent = "您的"
                    + CalculationUtil.diviUp(borrow.getAmount().longValue())
                    + "借款（合同编号：" + borrow.getCode() + "）已经成功还款，详情查看请登录APP。";
        } else if (EBoolean.NO.getCode().equals(approveResult)) {
            status = ERepayApplyStatus.APPROVE_NO.getCode();
            smsContent = "您的线下还款申请审核未通过，原因：" + approveNote + "。";
        }
        repayApplyBO.doApprove(repayApply, status, approver, approveNote);
        smsOutBO.sentContent(repayApply.getApplyUser(), smsContent);
    }

    private void doApproveStage(RepayApply repayApply, String result,
            String approver, String approveNote) {
        String status = null;
        String smsContent = null;
        if (EBoolean.YES.getCode().equals(result)) {
            status = ERepayApplyStatus.APPROVE_YES.getCode();
            Staging staging = stagingBO.getStaging(repayApply.getRefNo());
            if (EStagingStatus.TOREPAY.getCode().equals(staging.getStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(), "分期计划不处于待还款状态");
            }
            BorrowOrder order = borrowOrderBO.getBorrow(staging.getOrderCode());
            // 判断是否是最后一次分期
            StagingRule rule = stagingRuleBO.getStagingRule(order
                .getStageRuleCode());
            if (rule.getCount() == staging.getCount()) {
                // 发放优惠券
                List<BorrowOrder> orders = borrowOrderBO.getCouponOrders(order
                    .getApplyUser());
                Coupon counpon = couponBO.getCoupon(ECouponType.BORROW,
                    order.getCompanyCode());
                if (ECouponStatus.OPEN.getCode().equals(counpon.getStatus())
                        && counpon.getCondition() - orders.size() == 1) {
                    userCouponBO.saveUserCoupon(order.getApplyUser(), counpon,
                        "程序自动发放", "成功借还" + counpon.getCondition().toString()
                                + "次系统发放优惠券", counpon.getCompanyCode());
                    for (BorrowOrder borrowOrder : orders) {
                        borrowOrderBO.refreshIsCoupon(borrowOrder);
                    }
                    borrowOrderBO.refreshIsCoupon(order);
                }
                // 更新借款订单还款金额
                borrowOrderBO.repayOffline(order, staging.getPayAmount(),
                    approver);
            } else {
                // 更新借款订单还款金额
                borrowOrderBO.refreshStageRepay(order, staging.getMainAmount(),
                    approver);
            }
            stagingBO.refreshRepay(staging.getCode(),EPayType.OFFLINE.getCode(),repayApply.getCode());
            // 发送短信
            smsContent = "您的"
                    + CalculationUtil
                        .diviUp(staging.getPayAmount().longValue())
                    + "分期借款（分期编号：" + staging.getCode() + "）已经成功还款，详情查看请登录APP。";

        } else {
            status = ERepayApplyStatus.APPROVE_NO.getCode();
            smsContent = "您的线下还款申请审核未通过，原因：" + approveNote + "。";
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
            repayApply.setUser(userBO.getUser(repayApply.getApplyUser()));
            if (ERepayApplyType.REPAY.getCode().equals(repayApply.getType())) {
                repayApply.setBorrow(borrowOrderBO.getBorrow(repayApply
                    .getRefNo()));
            }
        }
        return results;
    }

    @Override
    public RepayApply getRepayApply(String code) {
        RepayApply repayApply = repayApplyBO.getRepayApply(code);
        repayApply.setUser(userBO.getUser(repayApply.getApplyUser()));
        if (ERepayApplyType.REPAY.getCode().equals(repayApply.getType())) {
            repayApply
                .setBorrow(borrowOrderBO.getBorrow(repayApply.getRefNo()));
        }
        return repayApply;
    }

    @Override
    @Transactional
    public String repayStage(String code) {
        Staging staging = stagingBO.getStaging(code);
        if (!EStagingStatus.TOREPAY.getCode().equals(staging.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "分期计划不处于待还款状态，不能进行还款操作");
        }
        List<RepayApply> result = repayApplyBO
            .queryCurrentRepayApplyList(staging.getApplyUser());
        if (result.size() > 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "您已经有一条待审核的还款申请，请勿重复提交");
        }
        Date now = new Date();
        if (staging.getStartPayDate().after(now)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "此次分期还未到开始还款日期，无法提前还款");
        }

        // 计息天数
        int days = DateUtil.daysBetween(staging.getStartPayDate(), new Date()) + 1;
        // 计算利息
        BigDecimal lxAmount = new BigDecimal(days).multiply(staging.getRate());
        // 还款金额
        BigDecimal payAmount = staging.getMainAmount().add(lxAmount);

        RepayApply repayApply = new RepayApply();
        String repayCode = OrderNoGenerater
            .generateM(EGeneratePrefix.REPAY_APPLY.getCode());
        repayApply.setCode(repayCode);
        repayApply.setRefNo(staging.getCode());
        repayApply.setType(ERepayApplyType.STAGE.getCode());
        repayApply.setAmount(payAmount);
        repayApply.setApplyUser(staging.getApplyUser());

        repayApply.setApplyNote("分期线下还款申请");
        repayApply.setApplyDatetime(now);
        repayApply.setStatus(ERepayApplyStatus.TO_APPROVE.getCode());
        repayApplyBO.saveRepayApply(repayApply);

        return repayCode;
    }

}
