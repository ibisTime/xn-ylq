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
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.ICertificationBO;
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
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.InfoContact;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.Renewal;
import com.cdkj.ylq.domain.RepayApply;
import com.cdkj.ylq.domain.SYSConfig;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBizType;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
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

    protected static final Logger logger = LoggerFactory
        .getLogger(IBorrowAO.class);

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
        Long xsAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getXsRate())) * product.getDuration();
        // 账户管理费
        Long glAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getGlRate())) * product.getDuration();
        // 服务费
        Long fwAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getFwRate())) * product.getDuration();
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
        borrow.setXsRate(product.getXsRate());
        borrow.setXsAmount(xsAmount);
        borrow.setGlRate(product.getGlRate());

        borrow.setGlAmount(glAmount);
        borrow.setFwRate(product.getFwRate());
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
                Integer step = sysConfigBO.getIntegerValue(
                    SysConstants.RENEWAL_STEP, ESystemCode.YLQ.getCode(),
                    ESystemCode.YLQ.getCode());
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
                    Long xsAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                        borrowAmount, borrow.getXsRate())) * step * cycle;
                    // 账户管理费
                    Long glAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                        borrowAmount, borrow.getGlRate())) * step * cycle;
                    // 服务费
                    Long fwAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                        borrowAmount, borrow.getFwRate())) * step * cycle;
                    // 续期总金额
                    Long totalAmount = borrow.getYqlxAmount() + lxAmount
                            + xsAmount + glAmount + fwAmount;
                    borrow.setRenewalStartDate(startDate);
                    borrow.setRenewalEndDate(endDate);
                    borrow.setRenewalAmount(totalAmount);
                }
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
            Integer step = sysConfigBO.getIntegerValue(
                SysConstants.RENEWAL_STEP, ESystemCode.YLQ.getCode(),
                ESystemCode.YLQ.getCode());
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
                Date endDate = DateUtil.getRelativeDate(startDate, step * cycle
                        * 24 * 3600 - 1);

                // 借款总额
                Long borrowAmount = borrow.getAmount();
                // 利息
                Long lxAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                    borrowAmount, borrow.getLxRate())) * step * cycle;
                // 快速信审费
                Long xsAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                    borrowAmount, borrow.getXsRate())) * step * cycle;
                // 账户管理费
                Long glAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                    borrowAmount, borrow.getGlRate())) * step * cycle;
                // 服务费
                Long fwAmount = AmountUtil.eraseLiUp(AmountUtil.mul(
                    borrowAmount, borrow.getFwRate())) * step * cycle;
                // 续期总金额
                Long totalAmount = borrow.getYqlxAmount() + lxAmount + xsAmount
                        + glAmount + fwAmount;
                borrow.setRenewalStartDate(startDate);
                borrow.setRenewalEndDate(endDate);
                borrow.setRenewalAmount(totalAmount);
            }
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
                    + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款未能审核通过，合同编号为" + borrow.getCode() + "，原因："
                    + approveNote + "。");
        }
        borrowBO.doApprove(borrow, status, approver, approveNote);
    }

    @Override
    @Transactional
    public void doLoan(String code, String result, String updater, String remark) {
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
            smsContent = "恭喜您，您的" + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款已经成功放款，合同编号为" + borrow.getCode() + "，详情查看请登录APP。";
        } else {
            borrowBO.loanFailure(borrow, updater, remark);
            smsContent = "很抱歉，您的" + CalculationUtil.diviUp(borrow.getAmount())
                    + "借款(合同编号:" + borrow.getCode() + ")放款失败，原因为：" + remark
                    + "，详情查看请登录APP。";
        }
        if (StringUtils.isNotBlank(smsContent)) {
            smsOutBO.sentContent(borrow.getApplyUser(), smsContent);
        }
    }

    @Override
    public void resubmitLoan(String code) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.LOAN_NO.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于打款失败状态，不能重新提交");
        }
        borrowBO.resubmitLoan(borrow);
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
        } else if (EPayType.OFFLINE.getCode().equals(payType)) {
            return doRepayOffline(borrow);
        } else {
            throw new BizException("xn6230000", "暂不支持此支付方式");
        }
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
    public String repaySuccess(String payGroup, String payType, String payCode,
            Long amount) {
        String userId = null;
        List<Borrow> borrowList = borrowBO.queryBorrowListByPayGroup(payGroup);
        if (CollectionUtils.isEmpty(borrowList)) {
            throw new BizException("XN000000", "找不到对应的借款记录");
        }
        Borrow borrow = borrowList.get(0);
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                || EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            // 更新订单支付金额
            borrowBO.repaySuccess(borrow, amount, payCode, payType);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.REPAY);
            // 额度重置为0
            certificationBO.resetSxAmount(borrow.getApplyUser());
            userId = borrow.getApplyUser();
            smsOutBO.sentContent(borrow.getApplyUser(),
                "您的" + CalculationUtil.diviUp(borrow.getAmount())
                        + "借款已经成功还款，合同编号为" + borrow.getCode() + "，详情查看请登录APP。");
        } else {
            logger.info("订单号：" + borrow.getCode() + "已支付，重复回调");
        }
        return userId;
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
        } else if (EPayType.OFFLINE.getCode().equals(payType)) {
            return doRenewalOffline(renewal);
        } else {
            throw new BizException("xn6230000", "暂不支持此支付方式");
        }
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
            renewal.getBorrowCode(), EBizType.YLQ_REPAY,
            EBizType.YLQ_RENEWAL.getValue() + "-微信", rmbAmount);
    }

    private Object doRenewalAlipay(Renewal renewal) {
        Long rmbAmount = renewal.getTotalAmount();
        User user = userBO.getRemoteUser(renewal.getApplyUser());
        return accountBO.doAlipayRemote(user.getUserId(),
            ESysUser.SYS_USER_YLQ.getCode(), renewal.getPayGroup(),
            renewal.getBorrowCode(), EBizType.YLQ_REPAY,
            EBizType.YLQ_RENEWAL.getValue() + "-支付宝", rmbAmount);
    }

    @Override
    @Transactional
    public String renewalSuccess(String payGroup, String payType,
            String payCode, Long amount) {
        String userId = null;
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
            // 更新借款订单
            borrowBO.renewalSuccess(borrow, renewal, amount);
            renewalBO.renewalSuccess(renewal, payCode, payType,
                borrow.getRenewalCount() + 1);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOANING);
            userId = borrow.getApplyUser();
            smsOutBO.sentContent(borrow.getApplyUser(),
                "您的" + CalculationUtil.diviUp(borrow.getAmount())
                        + "借款已经成功续期，合同编号为" + borrow.getCode() + "，详情查看请登录APP。");
        } else {
            logger.info("订单号：" + borrow.getCode() + "已支付，重复回调");
        }
        return userId;
    }

    @Override
    public void cuishou(String code) {
        List<String> mobiles = new ArrayList<String>();
        String userId = null;
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn6230000", "订单不处于逾期状态，不允许催收");
        }
        userId = borrow.getApplyUser();
        User user = userBO.getRemoteUser(userId);
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_CONTACT);
        InfoContact infoContact = JsonUtil.json2Bean(certification.getResult(),
            InfoContact.class);
        SYSConfig config = sysConfigBO.getSYSConfig(
            SysConstants.SEND_SMS_COUNT, ESystemCode.YLQ.getCode(),
            ESystemCode.YLQ.getCode());
        int count = Integer.valueOf(config.getCvalue());
        String content = "先生/女士，您好，请通知***（手机号）（身份证号隐藏其中几位），其在【九州宝】的欠款已严重逾期。若***不能及时处理，我司将要求其准备好相关材料（身份证、户口本等）等待相应司法流程，我司保留委托第三方机构上门催款的权利，届时产生的责任和影响由其本人承担！打扰之处敬请谅解，客服热线******，谢谢！【九州宝】";
        mobiles.add(infoContact.getFamilyMobile());
        mobiles.add(infoContact.getSocietyMobile());

        certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_CARRIER);
        String report = certification.getResult();

        // todo 去魔蝎联系人前N个
        for (String mobile : mobiles) {
            smsOutBO.sendContent(mobile, content, ESystemCode.YLQ.getCode(),
                ESystemCode.YLQ.getCode());
        }
    }

    @Override
    @Transactional
    public void confirmBad(String code, String updater, String remark) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("623073", "借款不处于逾期状态");
        }
        borrow.setStatus(EBorrowStatus.BAD.getCode());
        borrow.setUpdater(updater);
        borrow.setUpdateDatetime(new Date());
        borrow.setRemark(remark);
        borrowBO.confirmBad(borrow);

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
        logger.info("***************开始扫描即将到期借款***************");
        Borrow condition = new Borrow();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        condition.setStatusList(statusList);
        condition.setHkDatetime(DateUtil.getRelativeDateOfDays(
            DateUtil.getTodayEnd(), 1));
        List<Borrow> borrowList = borrowBO.queryBorrowList(condition);
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (Borrow borrow : borrowList) {
                smsOutBO.sentContent(borrow.getApplyUser(), "您的"
                        + CalculationUtil.diviUp(borrow.getAmount())
                        + "借款即将到期，合同编号为" + borrow.getCode()
                        + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。");
            }
        }
        logger.info("***************结束扫描即将到期借款***************");
    }

}
