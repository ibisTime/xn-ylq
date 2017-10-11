package com.cdkj.ylq.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IBorrowAO;
import com.cdkj.ylq.ao.ICouponConditionAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IContractBO;
import com.cdkj.ylq.bo.IOverdueBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.IRenewalBO;
import com.cdkj.ylq.bo.IRepayApplyBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.AmountUtil;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.CalculationUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.BaofooPay;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.InfoContact;
import com.cdkj.ylq.domain.MXReport;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.Renewal;
import com.cdkj.ylq.domain.RepayApply;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.dto.res.XN623091Res;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBizType;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EOverdueDeal;
import com.cdkj.ylq.enums.EPayType;
import com.cdkj.ylq.enums.ERenewalStatus;
import com.cdkj.ylq.enums.ERepayApplyStatus;
import com.cdkj.ylq.enums.ERepayApplyType;
import com.cdkj.ylq.enums.ESysUser;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;

@Service
public class BorrowAOImpl implements IBorrowAO {

    static final Logger logger = LoggerFactory.getLogger(BorrowAOImpl.class);

    @Autowired
    private IBorrowBO borrowBO;

    @Autowired
    private IApplyBO applyBO;

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private IUserCouponBO userCouponBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IRepayApplyBO repayApplyBO;

    @Autowired
    private IRenewalBO renewalBO;

    @Autowired
    private IOverdueBO overdueBO;

    @Autowired
    private ICouponConditionAO couponConditionAO;

    @Autowired
    private IContractBO contractBO;

    @Override
    @Transactional
    public String borrow(String userId, Long couponId) {
        User user = userBO.getRemoteUser(userId);
        if (EBoolean.YES.getCode().equals(user.getBlacklistFlag())) {
            throw new BizException("xn000000", "由于您逾期未还款，已被平台拉入黑名单，请联系平台进行处理！");
        }
        // 授信额度信息校验
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        if (certification == null) {
            throw new BizException("623070", "您还没有额度，请先选择产品进行申请");
        }
        InfoAmount infoAmount = JsonUtil.json2Bean(certification.getResult(),
            InfoAmount.class);
        if (infoAmount.getSxAmount() == 0) {
            throw new BizException("623070", "您还没有额度，请先选择产品进行申请");
        }
        if (StringUtils.isBlank(certification.getRef())) {
            throw new BizException("623070", "您还没有额度，请先选择产品进行申请");
        }
        if (ECertificationStatus.INVALID.getCode().equals(
            certification.getFlag())) {
            throw new BizException("623070", "您的额度已失效，请选择产品重新申请");
        }
        // 是否已经有借款
        if (borrowBO.getCurrentBorrow(userId) != null) {
            throw new BizException("623070", "当前已有借款");
        }
        // 产品
        Product product = productBO.getProduct(certification.getRef());

        String code = OrderNoGenerater.generateM(EGeneratePrefix.BORROW
            .getCode());
        Date now = new Date();
        // 优惠金额
        Long yhAmount = 0L;
        if (couponId != null) {
            UserCoupon userCoupon = userCouponBO.getUserCoupon(couponId);
            if (!EUserCouponStatus.TO_USE.getCode().equals(
                userCoupon.getStatus())) {
                throw new BizException("623070", "优惠券已不可使用");
            }
            if (infoAmount.getSxAmount() < userCoupon.getStartAmount()) {
                throw new BizException("623070", "不可使用该优惠券");
            }
            yhAmount = userCoupon.getAmount();
            userCouponBO.use(userCoupon, code);
        }
        // 借款总额
        Long borrowAmount = infoAmount.getSxAmount();
        // 利息
        Long lxAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getLxRate())) * product.getDuration();
        // 快速信审费
        Long xsAmount = product.getXsAmount();
        // 账户管理费
        Long glAmount = product.getGlAmount();
        // 服务费
        Long fwAmount = product.getFwAmount();
        // 应还金额
        Long totalAmount = borrowAmount;

        Borrow borrow = new Borrow();

        borrow.setCode(code);
        borrow.setApplyUser(userId);
        borrow.setSignDatetime(now);
        borrow.setAmount(borrowAmount);
        borrow.setLevel(product.getLevel());
        borrow.setDuration(product.getDuration());

        borrow.setLxRate(product.getLxRate());
        borrow.setLxAmount(lxAmount);
        borrow.setXsAmount(xsAmount);
        borrow.setGlAmount(glAmount);
        borrow.setFwAmount(fwAmount);

        borrow.setYhAmount(yhAmount);
        borrow.setRate1(product.getYqRate1());
        borrow.setRate2(product.getYqRate2());
        borrow.setYqlxAmount(0L);
        borrow.setYqDays(0);

        borrow.setTotalAmount(totalAmount);
        borrow.setRealHkAmount(0L);
        borrow.setRenewalCount(0);
        borrow.setStatus(EBorrowStatus.TO_APPROVE.getCode());
        borrow.setUpdater(userId);

        borrow.setUpdateDatetime(now);
        borrow.setRemark("新申请借款");

        borrowBO.saveBorrow(borrow);

        // 更新申请单状态
        applyBO.refreshCurrentApplyStatus(userId, EApplyStatus.TO_LOAN);

        // 额度减去
        certificationBO.refreshSxAmount(borrow.getApplyUser(),
            -borrow.getAmount());

        return code;
    }

    @Override
    public Paginable<Borrow> queryBorrowPage(int start, int limit,
            Borrow condition) {
        Paginable<Borrow> results = borrowBO.getPaginable(start, limit,
            condition);
        List<Borrow> borrowList = results.getList();
        for (Borrow borrow : borrowList) {
            borrow.setUser(userBO.getRemoteUser(borrow.getApplyUser()));
            borrow.setBankcard(accountBO.getBankcard(borrow.getApplyUser()));
            if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                    || EBorrowStatus.OVERDUE.getCode().equals(
                        borrow.getStatus())) {
                borrow.setRemainDays(DateUtil.daysBetween(new Date(),
                    borrow.getHkDatetime()));
            }
        }
        return results;
    }

    @Override
    public Paginable<Borrow> queryMyBorrowPage(int start, int limit,
            Borrow condition) {
        Paginable<Borrow> results = borrowBO.getPaginable(start, limit,
            condition);
        for (Borrow borrow : results.getList()) {
            if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                    || EBorrowStatus.OVERDUE.getCode().equals(
                        borrow.getStatus())) {
                Integer step = sysConfigBO
                    .getIntegerValue(SysConstants.RENEWAL_STEP);
                if (step > 0) {
                    Integer cycle = 1;
                    Date now = new Date();
                    Date startDate = null;
                    if (now.after(borrow.getHkDatetime())) {
                        startDate = DateUtil.getTomorrowStart(now);
                    } else {
                        startDate = DateUtil.getTomorrowStart(borrow
                            .getHkDatetime());
                    }
                    Date endDate = DateUtil.getRelativeDate(startDate, step
                            * cycle * 24 * 3600 - 1);

                    // 借款总额
                    Long borrowAmount = borrow.getAmount();
                    // 利息
                    Long lxAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                        borrowAmount, borrow.getLxRate())) * step * cycle;
                    // 快速信审费
                    Long xsAmount = borrow.getXsAmount() * cycle;
                    // 账户管理费
                    Long glAmount = borrow.getGlAmount() * cycle;
                    // 服务费
                    Long fwAmount = borrow.getFwAmount() * cycle;
                    // 续期总金额
                    Long totalAmount = borrow.getYqlxAmount() + lxAmount
                            + xsAmount + glAmount + fwAmount;
                    borrow.setRenewalStartDate(startDate);
                    borrow.setRenewalEndDate(endDate);
                    borrow.setRenewalAmount(totalAmount);
                }
                borrow.setRemainDays(DateUtil.daysBetween(new Date(),
                    borrow.getHkDatetime()));
            }

        }
        return results;
    }

    @Override
    public Borrow getBorrow(String code) {
        Borrow borrow = borrowBO.getBorrow(code);
        borrow.setUser(userBO.getRemoteUser(borrow.getApplyUser()));
        borrow.setBankcard(accountBO.getBankcard(borrow.getApplyUser()));
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                || EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            Integer step = sysConfigBO
                .getIntegerValue(SysConstants.RENEWAL_STEP);
            if (step > 0) {
                Integer cycle = 1;
                Date now = new Date();
                Date startDate = null;
                if (now.after(borrow.getHkDatetime())) {
                    startDate = DateUtil.getTodayStart();
                } else {
                    startDate = DateUtil.getTomorrowStart(borrow
                        .getHkDatetime());
                }
                Date endDate = DateUtil.getRelativeDate(startDate, step * cycle
                        * 24 * 3600 - 1);

                // 借款总额
                Long borrowAmount = borrow.getAmount();
                // 利息
                Long lxAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                    borrowAmount, borrow.getLxRate())) * step * cycle;
                // 快速信审费
                Long xsAmount = borrow.getXsAmount() * cycle;
                // 账户管理费
                Long glAmount = borrow.getGlAmount() * cycle;
                // 服务费
                Long fwAmount = borrow.getFwAmount() * cycle;
                // 续期总金额
                Long totalAmount = borrow.getYqlxAmount() + lxAmount + xsAmount
                        + glAmount + fwAmount;
                borrow.setRenewalStartDate(startDate);
                borrow.setRenewalEndDate(endDate);
                borrow.setRenewalAmount(totalAmount);
            }
            borrow.setRemainDays(DateUtil.daysBetween(new Date(),
                borrow.getHkDatetime()));
        }
        return borrow;
    }

    @Override
    @Transactional
    public void doApprove(String code, String approveResult, String approver,
            String approveNote) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.TO_APPROVE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn623000", "该申请记录不处于待审核状态");
        }
        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EBorrowStatus.APPROVE_YES.getCode();
        } else {
            status = EBorrowStatus.APPROVE_NO.getCode();
            // 返回优惠券
            userCouponBO.useCancel(borrow.getCode());
            // 更新申请单状态
            Certification certification = certificationBO.getCertification(
                borrow.getApplyUser(), ECertiKey.INFO_AMOUNT);
            if (ECertificationStatus.CERTI_YES.getCode().equals(
                certification.getFlag())) {
                applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                    EApplyStatus.APPROVE_YES);
            } else {
                applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                    EApplyStatus.CANCEL);
            }
            // 返还额度
            certificationBO.refreshSxAmount(borrow.getApplyUser(),
                borrow.getAmount());
            smsOutBO.sentContent(borrow.getApplyUser(), "很抱歉，您的"
                    + CalculationUtil.diviUp(borrow.getAmount()) + "借款（合同编号为"
                    + borrow.getCode() + ")额度使用受限，原因：" + approveNote
                    + "，详情请咨询客服。");
        }
        borrowBO.doApprove(borrow, status, approver, approveNote);
    }

    @Override
    @Transactional
    public void doLoanOffline(String code, String result, String updater,
            String remark) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.APPROVE_YES.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于待放款状态");
        }
        String smsContent = null;
        if (EBoolean.YES.getCode().equals(result)) {
            borrowBO.loanSuccess(borrow, updater, remark);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOANING);
            // 生成电子合同
            User user = userBO.getRemoteUser(borrow.getApplyUser());
            Bankcard bankcard = accountBO.getBankcard(borrow.getApplyUser());
            contractBO.generate(user, bankcard, borrow);
            // 短信通知
            smsContent = "恭喜您，您的" + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款已经成功放款，合同编号为" + borrow.getCode() + "，详情查看请登录APP。";
        } else {
            borrowBO.loanFailure(borrow, updater, remark);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOAN_NO);
            smsContent = "很抱歉，您的" + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款(合同编号:" + borrow.getCode() + ")放款失败，原因为：" + remark
                    + "，详情查看请登录APP。";
        }
        if (StringUtils.isNotBlank(smsContent)) {
            smsOutBO.sentContent(borrow.getApplyUser(), smsContent);
        }
    }

    @Override
    @Transactional
    public void doLoanBaofoo(String code, String updater, String remark) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.APPROVE_YES.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于待放款状态");
        }
        // 更新借款订单状态为申请代付中
        borrowBO.baofooPaySubmit(borrow, updater, remark);

        User user = userBO.getRemoteUser(borrow.getApplyUser());
        Bankcard bankcard = accountBO.getBankcard(borrow.getApplyUser());

        List<BaofooPay> baofooPayList = new ArrayList<BaofooPay>();
        BaofooPay baofooPay = new BaofooPay();
        baofooPay.setTransNo(code);
        baofooPay.setToAccName(user.getRealName());
        baofooPay.setToAccNo(bankcard.getBankcardNumber());
        baofooPay.setToBankName(bankcard.getBankName());
        baofooPay.setTransCardId(user.getIdNo());
        baofooPay.setTransMobile(user.getMobile());
        baofooPay.setTransMoney(borrow.getAmount() - borrow.getFwAmount()
                - borrow.getLxAmount() - borrow.getGlAmount()
                - borrow.getXsAmount() + borrow.getYhAmount());
        baofooPay.setTransSummary("九州宝-代付（借款订单：" + code + "）");
        baofooPayList.add(baofooPay);
        accountBO.baofooPay(baofooPayList);
    }

    @Override
    public void doLoanBaofooQuery(String code) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.PAY_SUBMIT.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于代付申请中状态");
        }
        List<String> borrowCodeList = new ArrayList<String>();
        borrowCodeList.add(code);
        accountBO.baofooPayQuery(borrowCodeList);
    }

    @Override
    public void doLoanBaofooQueryCallback(String code, boolean result,
            String remark) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.PAY_SUBMIT.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于宝付代付申请中");
        }
        if (result) {
            borrowBO.baofooPaySuccess(borrow);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOANING);
            // 生成电子合同
            User user = userBO.getRemoteUser(borrow.getApplyUser());
            Bankcard bankcard = accountBO.getBankcard(borrow.getApplyUser());
            contractBO.generate(user, bankcard, borrow);
            // 发送短信
            String smsContent = "恭喜您，您的"
                    + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款已经成功放款，合同编号为" + borrow.getCode() + "，详情查看请登录APP。";
            smsOutBO.sentContent(borrow.getApplyUser(), smsContent);
        } else {
            borrowBO.baofooPayFailure(borrow, remark);
        }
    }

    @Override
    @Transactional
    public void resubmitLoan(String code) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.LOAN_NO.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于打款失败状态，不能重新提交");
        }
        borrowBO.resubmitLoan(borrow);
        // 更新申请单状态
        applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
            EApplyStatus.TO_LOAN);
    }

    @Override
    public Object repay(String code, String payType) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                && !EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn6230000", "借款不处于待还款状态");
        }
        if (EPayType.ALIPAY.getCode().equals(payType)) {
            return doRepayAlipay(borrow);
        } else if (EPayType.WEIXIN_APP.getCode().equals(payType)) {
            return doRepayWechat(borrow);
        } else if (EPayType.BAOFOO_WITHHOLD.getCode().equals(payType)) {
            return doRepayBaofoo(borrow);
        } else if (EPayType.OFFLINE.getCode().equals(payType)) {
            return doRepayOffline(borrow);
        } else {
            throw new BizException("xn6230000", "暂不支持此支付方式");
        }
    }

    private Object doRepayBaofoo(Borrow borrow) {
        Long rmbAmount = borrow.getTotalAmount();
        User user = userBO.getRemoteUser(borrow.getApplyUser());
        Bankcard bankcard = accountBO.getBankcard(borrow.getApplyUser());
        boolean isSuccess = accountBO.baofooWithhold(bankcard.getBankCode(),
            bankcard.getBankcardNumber(), user.getIdNo(), user.getRealName(),
            user.getMobile(), rmbAmount, borrow.getCode());
        if (isSuccess) {
            // 如果是逾期还款，逾期记录落地
            if (borrow.getYqDays() > 0) {
                overdueBO.saveOverdue(borrow.getApplyUser(), borrow.getCode(),
                    borrow.getYqDays(), borrow.getYqlxAmount(),
                    EOverdueDeal.REPAY.getCode());
            }
            // 更新订单支付金额
            borrowBO.repaySuccess(borrow, rmbAmount, "宝付银行卡代扣",
                EPayType.BAOFOO_WITHHOLD.getCode());
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.REPAY);
            // 额度重置为0
            certificationBO.resetSxAmount(borrow.getApplyUser());
            // 发放优惠券
            couponConditionAO.repaySuccess(borrow.getApplyUser());
            // 发送短信
            smsOutBO.sentContent(borrow.getApplyUser(),
                "您的" + CalculationUtil.diviUp(borrow.getAmount()) + "借款（合同编号："
                        + borrow.getCode() + "）已经成功还款，详情查看请登录APP。");
        } else {
            throw new BizException("xn623000", "银行卡代扣失败，建议选择其他支付方式");
        }
        return new BooleanRes(true);
    }

    private Object doRepayOffline(Borrow borrow) {
        List<RepayApply> result = repayApplyBO
            .queryCurrentRepayApplyList(borrow.getApplyUser());
        if (result.size() > 0) {
            throw new BizException("xn623000", "您已经有一条待审核的打款申请，请勿重复提交");
        }
        RepayApply repayApply = new RepayApply();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.REPAY_APPLY
            .getCode());
        repayApply.setCode(code);
        repayApply.setRefNo(borrow.getCode());
        repayApply.setType(ERepayApplyType.REPAY.getCode());
        repayApply.setAmount(borrow.getTotalAmount());
        repayApply.setApplyUser(borrow.getApplyUser());

        repayApply.setApplyNote("线下还款申请");
        repayApply.setApplyDatetime(new Date());
        repayApply.setStatus(ERepayApplyStatus.TO_APPROVE.getCode());
        repayApplyBO.saveRepayApply(repayApply);

        return new BooleanRes(true);
    }

    private Object doRepayWechat(Borrow borrow) {
        Long rmbAmount = borrow.getTotalAmount();
        User user = userBO.getRemoteUser(borrow.getApplyUser());
        String payGroup = borrowBO.addPayGroup(borrow.getCode());
        return accountBO.doWeiXinPayRemote(user.getUserId(),
            ESysUser.SYS_USER_YLQ.getCode(), payGroup, borrow.getCode(),
            EBizType.YLQ_REPAY, EBizType.YLQ_REPAY.getValue() + "-微信",
            rmbAmount);
    }

    private Object doRepayAlipay(Borrow borrow) {
        Long rmbAmount = borrow.getTotalAmount();
        User user = userBO.getRemoteUser(borrow.getApplyUser());
        String payGroup = borrowBO.addPayGroup(borrow.getCode());
        return accountBO.doAlipayRemote(user.getUserId(),
            ESysUser.SYS_USER_YLQ.getCode(), payGroup, borrow.getCode(),
            EBizType.YLQ_REPAY, EBizType.YLQ_REPAY.getValue() + "-支付宝",
            rmbAmount);
    }

    @Override
    @Transactional
    public void repaySuccess(String payGroup, String payType, String payCode,
            Long amount) {
        List<Borrow> borrowList = borrowBO.queryBorrowListByPayGroup(payGroup);
        if (CollectionUtils.isEmpty(borrowList)) {
            throw new BizException("XN000000", "找不到对应的借款记录");
        }
        Borrow borrow = borrowList.get(0);
        User user = userBO.getRemoteUser(borrow.getApplyUser());
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                || EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            // 如果是逾期还款，逾期记录落地
            if (borrow.getYqDays() > 0) {
                overdueBO.saveOverdue(borrow.getApplyUser(), borrow.getCode(),
                    borrow.getYqDays(), borrow.getYqlxAmount(),
                    EOverdueDeal.REPAY.getCode());
            }
            // 更新订单支付金额
            borrowBO.repaySuccess(borrow, amount, payCode, payType);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.REPAY);
            // 额度重置为0
            certificationBO.resetSxAmount(borrow.getApplyUser());
            // 借还成功发放优惠券
            couponConditionAO.repaySuccess(borrow.getApplyUser());
            // 首次借还成功推荐人发放优惠券
            Borrow condition = new Borrow();
            condition.setApplyUser(borrow.getApplyUser());
            condition.setStatus(EBorrowStatus.REPAY.getCode());
            if (borrowBO.getTotalCount(condition) == 1) {
                if (StringUtils.isNotBlank(user.getUserReferee())) {
                    couponConditionAO.recommendSuccess(user.getUserReferee());
                }
            }
            // 发送短信
            smsOutBO.sentContent(borrow.getApplyUser(),
                "您的" + CalculationUtil.diviUp(borrow.getAmount()) + "借款（合同编号："
                        + borrow.getCode() + "）已经成功还款，详情查看请登录APP。");
        } else {
            logger.info("订单号：" + borrow.getCode() + "已支付，重复回调");
        }
    }

    @Override
    @Transactional
    public Object renewal(String code, String payType) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                && !EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn6230000", "借款不处于可以续期的状态");
        }
        // 落地待支付的续期记录
        Renewal renewal = renewalBO.applyRenewal(borrow);
        if (EPayType.ALIPAY.getCode().equals(payType)) {
            return doRenewalAlipay(renewal);
        } else if (EPayType.WEIXIN_APP.getCode().equals(payType)) {
            return doRenewalWechat(renewal);
        } else if (EPayType.BAOFOO_WITHHOLD.getCode().equals(payType)) {
            return doRenewalBaofoo(renewal);
        } else if (EPayType.OFFLINE.getCode().equals(payType)) {
            return doRenewalOffline(renewal);
        } else {
            throw new BizException("xn6230000", "暂不支持此支付方式");
        }
    }

    private Object doRenewalBaofoo(Renewal renewal) {
        Long rmbAmount = renewal.getTotalAmount();
        User user = userBO.getRemoteUser(renewal.getApplyUser());
        Bankcard bankcard = accountBO.getBankcard(renewal.getApplyUser());
        boolean isSuccess = accountBO.baofooWithhold(bankcard.getBankCode(),
            bankcard.getBankcardNumber(), user.getIdNo(), user.getRealName(),
            user.getMobile(), rmbAmount, renewal.getBorrowCode());
        if (isSuccess) {
            Borrow borrow = borrowBO.getBorrow(renewal.getBorrowCode());
            Integer renewalCount = borrow.getRenewalCount();
            // 如果是逾期还款，逾期记录落地
            if (borrow.getYqDays() > 0) {
                overdueBO.saveOverdue(borrow.getApplyUser(), borrow.getCode(),
                    borrow.getYqDays(), borrow.getYqlxAmount(),
                    EOverdueDeal.RENEWAL.getCode());
            }
            // 更新借款订单
            borrowBO.renewalSuccess(borrow, renewal, rmbAmount);
            // 更新续期记录
            renewalBO.renewalSuccess(renewal, "宝付银行卡代扣",
                EPayType.BAOFOO_WITHHOLD.getCode(), renewalCount + 1);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOANING);
            smsOutBO.sentContent(borrow.getApplyUser(),
                "您的" + CalculationUtil.diviUp(borrow.getAmount()) + "借款（合同编号："
                        + borrow.getCode() + "）已经成功续期，详情查看请登录APP。");
        } else {
            throw new BizException("xn623000", "银行卡代扣失败，建议选择其他支付方式");
        }
        return new BooleanRes(true);
    }

    private Object doRenewalOffline(Renewal renewal) {
        List<RepayApply> result = repayApplyBO
            .queryCurrentRepayApplyList(renewal.getApplyUser());
        if (result.size() > 0) {
            throw new BizException("xn623000", "您已经有一条待审核的打款申请，请勿重复提交");
        }
        RepayApply repayApply = new RepayApply();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.REPAY_APPLY
            .getCode());
        repayApply.setCode(code);
        repayApply.setRefNo(renewal.getCode());
        repayApply.setType(ERepayApplyType.RENEWAL.getCode());
        repayApply.setAmount(renewal.getTotalAmount());
        repayApply.setApplyUser(renewal.getApplyUser());

        repayApply.setApplyNote("线下续期申请");
        repayApply.setApplyDatetime(new Date());
        repayApply.setStatus(ERepayApplyStatus.TO_APPROVE.getCode());
        repayApplyBO.saveRepayApply(repayApply);

        return new BooleanRes(true);
    }

    private Object doRenewalWechat(Renewal renewal) {
        Long rmbAmount = renewal.getTotalAmount();
        User user = userBO.getRemoteUser(renewal.getApplyUser());
        return accountBO.doWeiXinPayRemote(user.getUserId(),
            ESysUser.SYS_USER_YLQ.getCode(), renewal.getPayGroup(),
            renewal.getBorrowCode(), EBizType.YLQ_RENEWAL,
            EBizType.YLQ_RENEWAL.getValue() + "-微信", rmbAmount);
    }

    private Object doRenewalAlipay(Renewal renewal) {
        Long rmbAmount = renewal.getTotalAmount();
        User user = userBO.getRemoteUser(renewal.getApplyUser());
        return accountBO.doAlipayRemote(user.getUserId(),
            ESysUser.SYS_USER_YLQ.getCode(), renewal.getPayGroup(),
            renewal.getBorrowCode(), EBizType.YLQ_RENEWAL,
            EBizType.YLQ_RENEWAL.getValue() + "-支付宝", rmbAmount);
    }

    @Override
    @Transactional
    public void renewalSuccess(String payGroup, String payType, String payCode,
            Long amount) {
        List<Renewal> renewalList = renewalBO
            .queryRenewalListByPayGroup(payGroup);
        if (CollectionUtils.isEmpty(renewalList)) {
            throw new BizException("XN000000", "找不到对应的续期记录");
        }
        Renewal renewal = renewalList.get(0);
        Borrow borrow = borrowBO.getBorrow(renewal.getBorrowCode());
        if (ERenewalStatus.TO_PAY.getCode().equals(renewal.getStatus())) {
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
            borrowBO.renewalSuccess(borrow, renewal, amount);
            // 更新续期记录
            renewalBO.renewalSuccess(renewal, payCode, payType,
                renewalCount + 1);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOANING);
            smsOutBO.sentContent(borrow.getApplyUser(),
                "您的" + CalculationUtil.diviUp(borrow.getAmount()) + "借款（合同编号："
                        + borrow.getCode() + "）已经成功续期，详情查看请登录APP。");
        } else {
            logger.info("订单号：" + borrow.getCode() + "已支付，重复回调");
        }
    }

    @Override
    public void cuishou(String code) {
        List<String> mobiles = new ArrayList<String>();
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn6230000", "订单不处于逾期状态，不允许催收");
        }
        String userId = borrow.getApplyUser();
        User user = userBO.getRemoteUser(userId);
        // 获取紧急联系人号码
        Certification certification = certificationBO.getCertification(
            borrow.getApplyUser(), ECertiKey.INFO_CONTACT);
        InfoContact infoContact = JsonUtil.json2Bean(certification.getResult(),
            InfoContact.class);
        mobiles.add(infoContact.getFamilyMobile());
        mobiles.add(infoContact.getSocietyMobile());

        // 获取运营商中排名前N个的联系人
        int count = sysConfigBO.getIntegerValue(SysConstants.SEND_SMS_COUNT);
        certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_CARRIER);
        String report = certification.getRef();
        MXReport mxReport = JsonUtil.json2Bean(report, MXReport.class);
        for (int i = 0; i < count; i++) {
            mobiles.add(mxReport.getCall_contact_detail().get(i).getPeer_num());
        }
        StringBuffer sb = new StringBuffer(user.getIdNo());
        String contentTemplate = sysConfigBO
            .getStringValue(SysConstants.SMS_CUISHOU);
        contentTemplate = String.format(contentTemplate, user.getMobile(), sb
            .replace(8, 11, "****").toString(), user.getMobile());

        // todo 去魔蝎联系人前N个
        for (String mobile : mobiles) {
            smsOutBO.sendContent(mobile, contentTemplate,
                ESystemCode.YLQ.getCode(), ESystemCode.YLQ.getCode());
        }
    }

    @Override
    @Transactional
    public void confirmBad(String code, String updater, String remark) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("623073", "借款不处于逾期状态");
        }
        // 更新借款订单信息
        borrowBO.confirmBad(borrow, updater, remark);
        // 如果是逾期还款，逾期记录落地
        if (borrow.getYqDays() > 0) {
            overdueBO.saveOverdue(borrow.getApplyUser(), borrow.getCode(),
                borrow.getYqDays(), borrow.getYqlxAmount(),
                EOverdueDeal.BAD.getCode());
        }
        // 更新申请单状态
        applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
            EApplyStatus.BAD);
        // 额度重置为0
        certificationBO.resetSxAmount(borrow.getApplyUser());
        // 将用户拉入黑名单
        userBO.addBlacklist(borrow.getApplyUser(), "bad_debt", updater,
            "借钱不还，已确认坏账");

    }

    @Override
    public void doCheckOverdueDaily() {
        logger.info("***************开始扫描逾期借款***************");
        Borrow condition = new Borrow();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        statusList.add(EBorrowStatus.OVERDUE.getCode());
        condition.setStatusList(statusList);
        condition.setCurDatetime(new Date());
        List<Borrow> borrowList = borrowBO.queryBorrowList(condition);
        if (borrowList != null) {
            logger.info("***************共扫描到" + borrowList.size()
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (Borrow borrow : borrowList) {
                overdue(borrow);
            }
        }
        logger.info("***************结束扫描逾期借款***************");
    }

    public void overdue(Borrow borrow) {
        // 逾期天数
        Integer yqDays = borrow.getYqDays() + 1;
        // 逾期利息
        Long yqlxAmount = borrow.getYqlxAmount();
        if (yqDays <= 7) {
            yqlxAmount += AmountUtil.eraseLiUp(AmountUtil.mul(
                borrow.getAmount(), borrow.getRate1()));
        } else {
            yqlxAmount += AmountUtil.eraseLiUp(AmountUtil.mul(
                borrow.getAmount(), borrow.getRate2()));
        }
        // 逾期利息封顶
        Double yqlxFdRate = sysConfigBO
            .getDoubleValue(SysConstants.YQLX_FD_RATE);
        Long fdAmount = AmountUtil.mul(borrow.getAmount(), yqlxFdRate);
        if (yqlxAmount > fdAmount) {
            yqlxAmount = fdAmount;
        }
        borrow.setYqDays(yqDays);
        borrow.setYqlxAmount(yqlxAmount);
        borrow.setTotalAmount(borrow.getAmount() + yqlxAmount);
        borrow.setStatus(EBorrowStatus.OVERDUE.getCode());
        borrow.setUpdater("程序自动");
        borrow.setUpdateDatetime(new Date());
        borrow.setRemark("已逾期");
        borrowBO.overdue(borrow);

        // 更新申请单状态
        applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
            EApplyStatus.OVERDUE);

    }

    @Override
    public void archive(String code, String updater, String remark) {
        Borrow data = borrowBO.getBorrow(code);
        data.setIsArchive(EBoolean.YES.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        borrowBO.archive(data);
    }

    @Override
    public void doCheckWillRepayDaily() {
        logger.info("***************开始扫描明日到期借款，短信提醒***************");
        Borrow condition = new Borrow();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        condition.setStatusList(statusList);
        condition.setHkDatetime(DateUtil.getRelativeDateOfDays(
            DateUtil.getTodayEnd(), 1));
        List<Borrow> borrowList = borrowBO.queryBorrowList(condition);
        if (borrowList != null) {
            logger.info("***************共扫描到" + borrowList.size()
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (Borrow borrow : borrowList) {
                smsOutBO.sentContent(borrow.getApplyUser(), "您的"
                        + CalculationUtil.diviUp(borrow.getAmount())
                        + "借款即将到期，合同编号为" + borrow.getCode()
                        + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。");
            }
        }
        logger.info("***************结束扫描明日到期借款***************");
    }

    @Override
    public void doAutoRepayDaily() {
        logger.info("***************开始扫描今日到期借款，自动扣款***************");
        Borrow condition = new Borrow();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        condition.setStatusList(statusList);
        condition.setHkDatetime(DateUtil.getTodayEnd());
        List<Borrow> borrowList = borrowBO.queryBorrowList(condition);
        if (borrowList != null) {
            logger.info("***************共扫描到" + borrowList.size()
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (Borrow borrow : borrowList) {
                // 自动扣款
                User user = userBO.getRemoteUser(borrow.getApplyUser());
                Bankcard bankcard = accountBO
                    .getBankcard(borrow.getApplyUser());
                boolean isSuccess = accountBO.baofooWithhold(
                    bankcard.getBankCode(), bankcard.getBankcardNumber(),
                    user.getIdNo(), user.getRealName(), user.getMobile(),
                    borrow.getTotalAmount(), borrow.getCode());
                if (isSuccess) {
                    // 如果是逾期还款，逾期记录落地
                    if (borrow.getYqDays() > 0) {
                        overdueBO.saveOverdue(borrow.getApplyUser(),
                            borrow.getCode(), borrow.getYqDays(),
                            borrow.getYqlxAmount(),
                            EOverdueDeal.REPAY.getCode());
                    }
                    // 更新订单支付金额
                    borrowBO.repaySuccess(borrow, borrow.getTotalAmount(),
                        "宝付银行卡代扣", EPayType.BAOFOO_WITHHOLD.getCode());
                    // 更新申请单状态
                    applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                        EApplyStatus.REPAY);
                    // 额度重置为0
                    certificationBO.resetSxAmount(borrow.getApplyUser());
                    // 还款成功
                    couponConditionAO.repaySuccess(borrow.getApplyUser());
                    smsOutBO.sentContent(borrow.getApplyUser(), "您的"
                            + CalculationUtil.diviUp(borrow.getAmount())
                            + "借款(合同编号:" + borrow.getCode()
                            + ")已经自动还款成功，详情查看请登录APP。");
                } else {
                    smsOutBO.sentContent(borrow.getApplyUser(), "您的"
                            + CalculationUtil.diviUp(borrow.getAmount())
                            + "借款(合同编号:" + borrow.getCode()
                            + ")自动扣款失败，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。");
                }
            }
        }
        logger.info("***************结束扫描今日到期借款***************");
    }

    @Override
    public void doBaofooPayQueryPerMinute() {
        logger.info("***************开始扫描已申请代付的订单，查询代付结果***************");
        Borrow condition = new Borrow();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.PAY_SUBMIT.getCode());
        condition.setStatusList(statusList);
        List<Borrow> borrowList = borrowBO.queryBorrowList(condition);
        if (borrowList != null) {
            logger.info("***************共扫描到" + borrowList.size()
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (Borrow borrow : borrowList) {
                // 自动查询结果
                List<String> borrowCodeList = new ArrayList<String>();
                borrowCodeList.add(borrow.getCode());
                accountBO.baofooPayQuery(borrowCodeList);
            }
        }
        logger.info("***************结束扫描已申请代付的订单***************");
    }

    @Override
    public XN623091Res isBorrowing(String userId) {
        XN623091Res res = new XN623091Res();
        res.setIsBorrowFlag(EBoolean.NO.getCode());
        Borrow borrow = borrowBO.getCurrentBorrow(userId);
        if (borrow != null) {
            res.setIsBorrowFlag(EBoolean.YES.getCode());
        }
        return res;
    }

    @Override
    public void editRemark(String code, String remark) {
        borrowBO.refreshRemark(code, remark);
    }

}
