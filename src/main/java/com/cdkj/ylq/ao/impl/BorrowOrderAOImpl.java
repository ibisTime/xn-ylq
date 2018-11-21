package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
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

import com.cdkj.ylq.ao.IBorrowOrderAO;
import com.cdkj.ylq.ao.ICouponConditionAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBorrowOrderBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IContractBO;
import com.cdkj.ylq.bo.IOverdueBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.IRepayApplyBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.CalculationUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.RepayApply;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.dto.res.XN623091Res;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EOverdueDeal;
import com.cdkj.ylq.enums.EPayType;
import com.cdkj.ylq.enums.ERepayApplyStatus;
import com.cdkj.ylq.enums.ERepayApplyType;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;

@Service
public class BorrowOrderAOImpl implements IBorrowOrderAO {

    static final Logger logger = LoggerFactory.getLogger(BorrowOrderAOImpl.class);

    @Autowired
    private IBorrowOrderBO borrowOrderBO;

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
    private IOverdueBO overdueBO;

    @Autowired
    private ICouponConditionAO couponConditionAO;

    @Autowired
    private IContractBO contractBO;

    @Override
    @Transactional
    public String borrow(String userId, Long couponId, String productCode) {
        User user = userBO.getUser(userId);
        if (EBoolean.YES.getCode().equals(user.getIsBlackList())) {
            throw new BizException("xn000000", "由于您逾期未还款，已被平台拉入黑名单，请联系平台进行处理！");
        }
        // 授信额度信息校验
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        if (certification == null) {
            throw new BizException("623070", "您的信用分为0，请先申请信用分");
        }
        InfoAmount infoAmount = JsonUtil.json2Bean(certification.getResult(),
            InfoAmount.class);
        if (infoAmount.getSxAmount().longValue() == 0) {
            throw new BizException("623070", "您的信用分为0，请先申请信用分");
        }
        if (StringUtils.isBlank(certification.getRef())) {
            throw new BizException("623070", "您的信用分为0，请先申请信用分");
        }
        if (ECertificationStatus.INVALID.getCode().equals(
            certification.getFlag())) {
            throw new BizException("623070", "您的信用分已失效，请重新申请信用分");
        }
        // 是否已经有借款
        if (borrowOrderBO.getCurrentBorrow(userId) != null) {
            throw new BizException("623070", "当前已有借款");
        }
        // 产品
        Product product = productBO.getProduct(productCode);

        String code = OrderNoGenerater.generateM(EGeneratePrefix.BORROW
            .getCode());
        Date now = new Date();
        // 优惠金额
        BigDecimal yhAmount = BigDecimal.ZERO;
        if (couponId != null) {
            UserCoupon userCoupon = userCouponBO.getUserCoupon(couponId);
            if (!EUserCouponStatus.TO_USE.getCode().equals(
                userCoupon.getStatus())) {
                throw new BizException("623070", "优惠券已不可使用");
            }
            if (infoAmount.getSxAmount().longValue() < userCoupon
                .getStartAmount()) {
                throw new BizException("623070", "不可使用该优惠券");
            }
            yhAmount = userCoupon.getAmount();
            userCouponBO.use(userCoupon, code);
        }
        // 借款总额
        BigDecimal borrowAmount = infoAmount.getSxAmount();
        // 利息
        BigDecimal lxAmount = borrowAmount.multiply(product.getLxRate())
            .multiply(new BigDecimal(product.getDuration()))
            .setScale(2, BigDecimal.ROUND_UP);
        // 快速信审费
        BigDecimal xsAmount = product.getXsAmount();
        // 账户管理费
        BigDecimal glAmount = product.getGlAmount();
        // 服务费
        BigDecimal fwAmount = product.getFwAmount();
        // 应还金额
        BigDecimal totalAmount = borrowAmount;

        BorrowOrder borrow = new BorrowOrder();

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

        borrow.setTotalAmount(totalAmount);
        borrow.setStatus(EBorrowStatus.TO_APPROVE.getCode());
        borrow.setUpdater(userId);

        borrow.setUpdateDatetime(now);
        borrow.setRemark("新申请借款");
        borrow.setCompanyCode(user.getCompanyCode());
        borrowOrderBO.saveBorrow(borrow);

        // 更新申请单状态
        applyBO.refreshCurrentApplyStatus(userId, EApplyStatus.TO_LOAN);

        // 额度减去
        certificationBO.refreshSxAmount(borrow.getApplyUser(), borrow
            .getAmount().negate());

        return code;
    }

    @Override
    public Paginable<BorrowOrder> queryBorrowPage(int start, int limit,
            BorrowOrder condition) {
        Paginable<BorrowOrder> results = borrowOrderBO.getPaginable(start,
            limit, condition);
        List<BorrowOrder> borrowList = results.getList();
        for (BorrowOrder borrow : borrowList) {
            borrow.setUser(userBO.getRemoteUser(borrow.getApplyUser()));
            borrow.setBankcard(accountBO.getBankcard(borrow.getApplyUser()));
            if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                    || EBorrowStatus.OVERDUE.getCode().equals(
                        borrow.getStatus())) {
                borrow.setRemainDays(DateUtil.daysBetween(
                    DateUtil.getTodayStart(),
                    DateUtil.getTomorrowStart(borrow.getHkDatetime())));
            }
        }
        return results;
    }

    @Override
    public Paginable<BorrowOrder> queryMyBorrowPage(int start, int limit,
            BorrowOrder condition) {
        Paginable<BorrowOrder> results = borrowOrderBO.getPaginable(start,
            limit, condition);
        for (BorrowOrder borrow : results.getList()) {
            if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                    || EBorrowStatus.OVERDUE.getCode().equals(
                        borrow.getStatus())) {
                borrow.setRemainDays(DateUtil.daysBetween(
                    DateUtil.getTodayStart(),
                    DateUtil.getTomorrowStart(borrow.getHkDatetime())));
            }

        }
        return results;
    }

    @Override
    public BorrowOrder getBorrow(String code) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        borrow.setUser(userBO.getRemoteUser(borrow.getApplyUser()));
        borrow.setBankcard(accountBO.getBankcard(borrow.getApplyUser()));
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                || EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            borrow.setRemainDays(DateUtil.daysBetween(DateUtil.getTodayStart(),
                DateUtil.getTomorrowStart(borrow.getHkDatetime())));
        }
        return borrow;
    }

    @Override
    @Transactional
    public void doApprove(String code, String approveResult, String approver,
            String approveNote) {
        // 订单检验
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        // 状态检验
        if (!EBorrowStatus.TO_APPROVE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn623000", "该申请记录不处于待审核状态");
        }
        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EBorrowStatus.APPROVE_YES.getCode();
        } else {
            status = EBorrowStatus.APPROVE_NO.getCode();
            // 使用优惠券
            userCouponBO.useCancel(borrow.getCode());
            // 更新授信额度
            Certification certification = certificationBO.getCertification(
                borrow.getApplyUser(), ECertiKey.INFO_AMOUNT);
            //
            if (ECertificationStatus.CERTI_YES.getCode().equals(
                certification.getFlag())) {
                applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                    EApplyStatus.APPROVE_YES);
            } else {
                applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                    EApplyStatus.CANCEL);
            }
            // 返还额度信用分
            certificationBO.refreshSxAmount(borrow.getApplyUser(),
                borrow.getAmount());
            smsOutBO.sentContent(borrow.getApplyUser(), "很抱歉，您的"
                    + CalculationUtil.diviUp(borrow.getAmount().longValue())
                    + "借款（合同编号为" + borrow.getCode() + ")额度使用受限，原因："
                    + approveNote + "，详情请咨询客服。");
        }
        borrowOrderBO.doApprove(borrow, status, approver, approveNote);
    }

    @Override
    @Transactional
    public void doLoanOffline(String code, String result, String updater,
            String remark) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        if (!EBorrowStatus.APPROVE_YES.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于待放款状态");
        }
        String smsContent = null;
        if (EBoolean.YES.getCode().equals(result)) {
            borrowOrderBO.loanSuccess(borrow, updater, remark);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOANING);
            // 生成电子合同
            User user = userBO.getRemoteUser(borrow.getApplyUser());
            Bankcard bankcard = accountBO.getBankcard(borrow.getApplyUser());
            contractBO.generate(user, bankcard, borrow);
            // 首次借款成功推荐人发放优惠券
            BorrowOrder condition = new BorrowOrder();
            condition.setApplyUser(borrow.getApplyUser());
            condition.setStatus(EBorrowStatus.REPAY.getCode());
            if (borrowOrderBO.getTotalCount(condition) == 1) {
                if (StringUtils.isNotBlank(user.getUserReferee())) {
                    couponConditionAO.recommendSuccess(user.getUserReferee());
                }
            }
            // 短信通知
            smsContent = "恭喜您，您的"
                    + CalculationUtil.diviUp(borrow.getAmount().longValue())
                    + "借款已经成功放款，合同编号为" + borrow.getCode() + "，详情查看请登录APP。";
        } else {
            borrowOrderBO.loanFailure(borrow, updater, remark);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.LOAN_NO);
            smsContent = "很抱歉，您的"
                    + CalculationUtil.diviUp(borrow.getAmount().longValue())
                    + "借款(合同编号:" + borrow.getCode() + ")放款失败，原因为：" + remark
                    + "，详情查看请登录APP。";
        }
        if (StringUtils.isNotBlank(smsContent)) {
            smsOutBO.sentContent(borrow.getApplyUser(), smsContent);
        }
    }

    @Override
    @Transactional
    public void resubmitLoan(String code) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        if (!EBorrowStatus.LOAN_NO.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于打款失败状态，不能重新提交");
        }
        borrowOrderBO.resubmitLoan(borrow);
        // // 更新申请单状态
        // applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
        // EApplyStatus.TO_LOAN);
    }

    @Override
    public Object repay(String code, String payType) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                && !EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn6230000", "借款不处于待还款状态");
        }
        if (EPayType.OFFLINE.getCode().equals(payType)) {
            return doRepayOffline(borrow);
        } else {
            throw new BizException("xn6230000", "暂不支持此支付方式");
        }
    }

    private Object doRepayOffline(BorrowOrder borrow) {
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

    @Override
    @Transactional
    public void repaySuccess(String payGroup, String payType, String payCode,
            BigDecimal amount) {
        List<BorrowOrder> borrowList = borrowOrderBO
            .queryBorrowListByPayGroup(payGroup);
        if (CollectionUtils.isEmpty(borrowList)) {
            throw new BizException("XN000000", "找不到对应的借款记录");
        }
        BorrowOrder borrow = borrowList.get(0);
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                || EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            // 如果是逾期还款，逾期记录落地
            if (borrow.getYqDays() > 0) {
                overdueBO.saveOverdue(borrow.getApplyUser(), borrow.getCode(),
                    borrow.getYqDays(), borrow.getYqlxAmount(),
                    EOverdueDeal.REPAY.getCode());
            }
            // 更新订单支付金额
            borrowOrderBO.repaySuccess(borrow, amount, payCode, payType);
            // 更新申请单状态
            applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
                EApplyStatus.REPAY);
            // 额度重置为0
            certificationBO.resetSxAmount(borrow.getApplyUser());
            // 借还成功发放优惠券
            couponConditionAO.repaySuccess(borrow.getApplyUser());
            // 发送短信
            smsOutBO
                .sentContent(
                    borrow.getApplyUser(),
                    "您的"
                            + CalculationUtil.diviUp(borrow.getAmount()
                                .longValue()) + "借款（合同编号：" + borrow.getCode()
                            + "）已经成功还款，详情查看请登录APP。");
        } else {
            logger.info("订单号：" + borrow.getCode() + "已支付，重复回调");
        }
    }

    @Override
    public void cuishou(String code) {
        // List<String> mobiles = new ArrayList<String>();
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        if (!EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn6230000", "订单不处于逾期状态，不允许催收");
        }
        String userId = borrow.getApplyUser();
        User user = userBO.getRemoteUser(userId);

        StringBuffer sb = new StringBuffer(user.getIdNo());
        String contentTemplate = sysConfigBO
            .getStringValue(SysConstants.SMS_CUISHOU);
        contentTemplate = String.format(contentTemplate, user.getMobile(), sb
            .replace(8, 11, "****").toString(), user.getMobile());
        // 向本人发送催收短信
        smsOutBO.sendContent(user.getMobile(), contentTemplate,
            ESystemCode.YLQ.getCode(), ESystemCode.YLQ.getCode());

        // // 获取紧急联系人号码
        // Certification certification = certificationBO.getCertification(
        // borrow.getApplyUser(), ECertiKey.INFO_CONTACT);
        // InfoContact infoContact =
        // JsonUtil.json2Bean(certification.getResult(),
        // InfoContact.class);
        // mobiles.add(infoContact.getFamilyMobile());
        // mobiles.add(infoContact.getSocietyMobile());
        //
        // // 获取运营商中排名前N个的联系人
        // int count = sysConfigBO.getIntegerValue(SysConstants.SEND_SMS_COUNT);
        // certification = certificationBO.getCertification(userId,
        // ECertiKey.INFO_CARRIER);
        // String report = certification.getRef();
        // MXReport mxReport = JsonUtil.json2Bean(report, MXReport.class);
        // for (int i = 0; i < count; i++) {
        // mobiles.add(mxReport.getCall_contact_detail().get(i).getPeer_num());
        // }
        // StringBuffer sb = new StringBuffer(user.getIdNo());
        // String contentTemplate = sysConfigBO
        // .getStringValue(SysConstants.SMS_CUISHOU);
        // contentTemplate = String.format(contentTemplate, user.getMobile(), sb
        // .replace(8, 11, "****").toString(), user.getMobile());
        //
        // // todo 去魔蝎联系人前N个
        // for (String mobile : mobiles) {
        // smsOutBO.sendContent(mobile, contentTemplate,
        // ESystemCode.YLQ.getCode(), ESystemCode.YLQ.getCode());
        // }
    }

    @Override
    @Transactional
    public void confirmBad(String code, String updater, String remark) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        if (!EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("623073", "借款不处于逾期状态");
        }
        // 更新借款订单信息
        borrowOrderBO.confirmBad(borrow, updater, remark);
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
        BorrowOrder condition = new BorrowOrder();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        statusList.add(EBorrowStatus.OVERDUE.getCode());
        condition.setStatusList(statusList);
        condition.setCurDatetime(new Date());
        List<BorrowOrder> borrowList = borrowOrderBO.queryBorrowList(condition);
        if (borrowList != null) {
            logger.info("***************共扫描到" + borrowList.size()
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (BorrowOrder borrow : borrowList) {
                overdue(borrow);
            }
        }
        logger.info("***************结束扫描逾期借款***************");
    }

    public void overdue(BorrowOrder borrow) {
        // 逾期天数
        Integer yqDays = borrow.getYqDays() + 1;
        // 逾期利息
        BigDecimal yqlxAmount = borrow.getYqlxAmount();
        if (yqDays <= 7) {

            yqlxAmount = yqlxAmount.add(borrow.getAmount()
                .multiply(borrow.getRate1()).setScale(2, BigDecimal.ROUND_UP));
        } else {
            yqlxAmount = yqlxAmount.add(borrow.getAmount()
                .multiply(borrow.getRate2()).setScale(2, BigDecimal.ROUND_UP));
        }
        // 逾期利息封顶
        BigDecimal yqlxFdRate = BigDecimal.valueOf(sysConfigBO
            .getDoubleValue(SysConstants.YQLX_FD_RATE));
        BigDecimal fdAmount = borrow.getAmount().multiply(yqlxFdRate);
        if (yqlxAmount.compareTo(fdAmount) > 0) {
            yqlxAmount = fdAmount;
        }
        borrow.setYqDays(yqDays);
        borrow.setYqlxAmount(yqlxAmount);
        borrow.setTotalAmount(borrow.getAmount().add(yqlxAmount));
        borrow.setStatus(EBorrowStatus.OVERDUE.getCode());
        borrow.setUpdater("程序自动");
        borrow.setUpdateDatetime(new Date());
        borrow.setRemark("已逾期");
        borrowOrderBO.overdue(borrow);

        // 更新申请单状态
        applyBO.refreshCurrentApplyStatus(borrow.getApplyUser(),
            EApplyStatus.OVERDUE);

    }

    @Override
    public void archive(String code, String updater, String remark) {
        BorrowOrder data = borrowOrderBO.getBorrow(code);
        data.setIsArchive(EBoolean.YES.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        borrowOrderBO.archive(data);
    }

    @Override
    public void doCheckWillRepayDaily() {
        logger.info("***************开始扫描明日到期借款，短信提醒***************");
        BorrowOrder condition = new BorrowOrder();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        condition.setStatusList(statusList);
        condition.setHkDatetime(DateUtil.getRelativeDateOfDays(
            DateUtil.getTodayEnd(), 1));
        List<BorrowOrder> borrowList = borrowOrderBO.queryBorrowList(condition);
        if (borrowList != null) {
            logger.info("***************共扫描到" + borrowList.size()
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (BorrowOrder borrow : borrowList) {
                smsOutBO.sentContent(
                    borrow.getApplyUser(),
                    "您的"
                            + CalculationUtil.diviUp(borrow.getAmount()
                                .longValue()) + "借款即将到期，合同编号为"
                            + borrow.getCode()
                            + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。");
            }
        }
        logger.info("***************结束扫描明日到期借款***************");
    }

    @Override
    public XN623091Res isBorrowing(String userId) {
        XN623091Res res = new XN623091Res();
        res.setIsBorrowFlag(EBoolean.NO.getCode());
        BorrowOrder borrow = borrowOrderBO.getCurrentBorrow(userId);
        if (borrow != null) {
            res.setIsBorrowFlag(EBoolean.YES.getCode());
        }
        return res;
    }

    @Override
    public void editRemark(String code, String remark) {
        borrowOrderBO.refreshRemark(code, remark);
    }

}
