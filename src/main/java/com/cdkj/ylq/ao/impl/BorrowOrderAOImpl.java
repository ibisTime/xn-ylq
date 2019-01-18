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
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBorrowOrderBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.INoticerBO;
import com.cdkj.ylq.bo.IOverdueBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.IRepayApplyBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IStagingBO;
import com.cdkj.ylq.bo.IStagingRuleBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.Page;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.CalculationUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.Noticer;
import com.cdkj.ylq.domain.Overdue;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.RepayApply;
import com.cdkj.ylq.domain.StageInfo;
import com.cdkj.ylq.domain.Staging;
import com.cdkj.ylq.domain.StagingRule;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.dto.res.XN623091Res;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.ENoticerType;
import com.cdkj.ylq.enums.EOverdueDeal;
import com.cdkj.ylq.enums.ERepayApplyStatus;
import com.cdkj.ylq.enums.ERepayApplyType;
import com.cdkj.ylq.enums.EStagingStatus;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

@Service
public class BorrowOrderAOImpl implements IBorrowOrderAO {

    static final Logger logger = LoggerFactory
        .getLogger(BorrowOrderAOImpl.class);

    @Autowired
    private IBorrowOrderBO borrowOrderBO;

    @Autowired
    private IStagingRuleBO stagingRuleBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IApplyBO applyBO;

    @Autowired
    private INoticerBO noticerBO;

    @Autowired
    private IStagingBO stagingBO;

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
        if (infoAmount.getSxAmount().compareTo(BigDecimal.ZERO) == 0) {
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
            if (infoAmount.getSxAmount().compareTo(userCoupon.getStartAmount()) < 0) {
                throw new BizException("623070", "不可使用该优惠券");
            }
            yhAmount = userCoupon.getAmount();
            userCouponBO.use(userCoupon, code);
        }
        // 借款总额
        BigDecimal borrowAmount = product.getAmount();
        if (borrowAmount.compareTo(infoAmount.getSxAmount()) > 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "您的信用分不足购买该产品");
        }
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
        borrow.setBorrowAmunt(borrowAmount);
        borrow.setRealGetAmount(borrowAmount.subtract(lxAmount)
            .subtract(xsAmount).subtract(glAmount).subtract(fwAmount)
            .add(yhAmount));
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
        borrow.setRealHkAmount(BigDecimal.ZERO);
        borrow.setYqlxAmount(BigDecimal.ZERO);
        borrow.setYqDays(0);
        borrow.setTotalAmount(totalAmount);
        borrow.setStatus(EBorrowStatus.TO_APPROVE.getCode());
        borrow.setUpdater(userId);
        borrow.setIsStage(EBoolean.NO.getCode());
        borrow.setStageBatch(0);
        borrow.setIsArchive(EBoolean.NO.getCode());
        borrow.setIsCoupon(EBoolean.NO.getCode());
        borrow.setUpdateDatetime(now);
        borrow.setRemark("新申请借款");
        borrow.setCompanyCode(user.getCompanyCode());
        borrowOrderBO.saveBorrow(borrow);

        // 更新申请单状态
        // applyBO.refreshCurrentApplyStatus(userId, EApplyStatus.TO_LOAN);

        // 额度减去
        certificationBO.refreshSxAmount(borrow.getApplyUser(), borrow
            .getAmount().negate());
        // 通知审批人
        List<Noticer> noticers = noticerBO.queryNoticersNow(
            ENoticerType.Approver.getCode(), user.getCompanyCode());
        String sendContent = "用户：" + user.getMobile() + "有一笔借款等待审批，请尽早在管理端进行审批";
        if (!noticers.isEmpty()) {
            for (Noticer noticer : noticers) {
                smsOutBO.sendContent(noticer.getMobile(), sendContent,
                    borrow.getCompanyCode(), ESystemCode.YLQ.getCode());
            }
        }
        return code;
    }

    private void initStageList(BorrowOrder order) {
        Date now = new Date();
        List<StageInfo> infoList = new ArrayList<StageInfo>();

        StageInfo stageInfo = new StageInfo();

        // 本次分期的分期计划列表（实时计算应还金额）
        List<Staging> stageList = stagingBO.queryBorrowStagings(order);

        Long repayCount = order.getRepayCount();
        for (Staging staging : stageList) {
            if (staging.getCount() == repayCount + 1) {
                if (now.before(staging.getStartPayDate())) {
                    stageInfo.setStageCode(staging.getCode());
                    stageInfo.setLxAmount(staging.getRate().multiply(
                        order.getAmount()));
                    stageInfo.setMainAmount(staging.getMainAmount());
                    stageInfo.setAmount(stageInfo.getLxAmount().add(
                        stageInfo.getMainAmount()));
                    stageInfo.setDate(DateUtil.dateToStr(
                        staging.getStartPayDate(),
                        DateUtil.FRONT_DATE_FORMAT_STRING));
                    stageInfo.setStartTime(staging.getStartPayDate());
                    stageInfo.setEndTime(staging.getLastPayDate());
                    stageInfo.setStageCount(staging.getCount().intValue());
                    stageInfo.setRemark("第" + staging.getCount() + "期第1天");
                    order.setInfo(stageInfo);
                } else {
                    Date startDate = now;
                    // 距离开始时间已有几天
                    int days = DateUtil.daysBetween(staging.getStartPayDate(),
                        startDate) + 1;
                    // 利息=利率*天数*分期总本金
                    BigDecimal lxAmount = staging.getRate()
                        .multiply(new BigDecimal(days))
                        .multiply(order.getAmount());
                    // 可以开始还款，覆盖初始化的分期数据
                    StageInfo info = new StageInfo();
                    info.setStageCode(staging.getCode());
                    info.setLxAmount(lxAmount);
                    info.setMainAmount(staging.getMainAmount());
                    info.setAmount(lxAmount.add(staging.getMainAmount()));
                    info.setDate(DateUtil.dateToStr(startDate,
                        DateUtil.FRONT_DATE_FORMAT_STRING));
                    info.setStartTime(staging.getStartPayDate());
                    info.setEndTime(staging.getLastPayDate());
                    info.setStatus(staging.getStatus());
                    info.setStageCount(staging.getCount().intValue());
                    info.setRemark("第" + staging.getCount() + "期，第" + days
                            + "天");
                    order.setInfo(info);
                }
            }
        }
        for (Staging staging : stageList) {
            if (EStagingStatus.REPAY.getCode().equals(staging.getStatus())) {
                // 构建还款信息
                StageInfo info = new StageInfo();
                info.setStageCode(staging.getCode());
                info.setLxAmount(staging.getPayAmount().subtract(
                    staging.getMainAmount()));
                info.setMainAmount(staging.getMainAmount());
                info.setAmount(staging.getPayAmount());
                info.setDate(DateUtil.dateToStr(staging.getPayDatetime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING));
                info.setStatus(staging.getStatus());
                info.setStageCount(staging.getCount().intValue());
                info.setRemark("第" + staging.getCount() + "期已还款");
                infoList.add(info);
            } else {
                Date startDate = staging.getStartPayDate();

                for (; startDate.before(staging.getLastPayDate()); startDate = DateUtil
                    .getRelativeDateOfDays(startDate, 1)) {
                    // 距离开始时间已有几天
                    int days = DateUtil.daysBetween(staging.getStartPayDate(),
                        startDate) + 1;
                    // 利息=利率*天数*分期总本金
                    BigDecimal lxAmount = staging.getRate()
                        .multiply(new BigDecimal(days))
                        .multiply(order.getAmount());
                    // 构建还款信息
                    StageInfo info = new StageInfo();
                    info.setStageCode(staging.getCode());
                    info.setLxAmount(lxAmount);
                    info.setMainAmount(staging.getMainAmount());
                    info.setAmount(lxAmount.add(staging.getMainAmount()));
                    info.setDate(DateUtil.dateToStr(startDate,
                        DateUtil.FRONT_DATE_FORMAT_STRING));
                    info.setStatus(staging.getStatus());
                    info.setStageCount(staging.getCount().intValue());
                    info.setRemark("第" + staging.getCount() + "期，第" + days
                            + "天");
                    infoList.add(info);
                }
            }
        }
        order.setStageList(infoList);
    }

    @Override
    public Paginable<BorrowOrder> queryBorrowPage(int start, int limit,
            BorrowOrder condition) {
        Paginable<BorrowOrder> results = borrowOrderBO.getPaginable(start,
            limit, condition);
        List<BorrowOrder> borrowList = results.getList();
        for (BorrowOrder borrow : borrowList) {
            Overdue con = new Overdue();
            con.setUserId(borrow.getApplyUser());
            Integer yqCount = (int) overdueBO.getTotalCount(con);
            borrow.setYqCount(yqCount);
            borrow.setUser(userBO.getUser(borrow.getApplyUser()));
            borrow.setBankcard(accountBO.getBankcard(borrow.getApplyUser()));
            if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())) {
                if (EBoolean.NO.getCode().equals(borrow.getIsStage())) {
                    borrow.setRemainDays(DateUtil.daysBetween(
                        DateUtil.getTodayStart(),
                        DateUtil.getTomorrowStart(borrow.getHkDatetime())));
                }
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
        borrow.setUser(userBO.getUser(borrow.getApplyUser()));
        borrow.setBankcard(accountBO.getBankcard(borrow.getApplyUser()));
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                || EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            borrow.setRemainDays(DateUtil.daysBetween(DateUtil.getTodayStart(),
                DateUtil.getTomorrowStart(borrow.getHkDatetime())));
        }
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                && EBoolean.YES.getCode().equals(borrow.getIsStage())
                && borrow.getRepayCount() != null) {
            initStageList(borrow);
        }
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                || EBorrowStatus.REPAY.getCode().equals(borrow.getStatus())
                || EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())
                || EBorrowStatus.BAD.getCode().equals(borrow.getStatus())) {
            initRepayList(borrow);
        }
        User user = borrow.getUser();
        Integer count = borrowOrderBO.getTotalBorrowCount(user.getUserId());
        borrow.setBorrowCount(count);
        Overdue condition = new Overdue();
        condition.setUserId(borrow.getApplyUser());
        Integer yqCount = (int) overdueBO.getTotalCount(condition);
        borrow.setYqCount(yqCount);
        return borrow;
    }

    private void initRepayList(BorrowOrder borrow) {
        Staging condition = new Staging();
        condition.setOrderCode(borrow.getCode());
        condition.setStatus(EStagingStatus.REPAY.getCode());
        List<Staging> stagings = stagingBO.queryStagingList(condition);
        List<RepayApply> repayList = new ArrayList<RepayApply>();
        for (Staging staging : stagings) {
            RepayApply apply = repayApplyBO.getApplyByRef(staging.getCode());
            int days = DateUtil.daysBetween(staging.getStartPayDate(),
                staging.getPayDatetime()) + 1;
            apply.setRemark("第" + staging.getBatch() + "次分期：第"
                    + staging.getCount() + "期，第" + days + "天");
            repayList.add(apply);
        }
        RepayApply apply = repayApplyBO.getApplyByRef(borrow.getCode());
        if (apply != null) {
            repayList.add(apply);
        }
        borrow.setRepayList(repayList);

    }

    @Override
    @Transactional
    public void doApprove(String code, String approveResult, String approver,
            String approveNote) {
        // 订单检验
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        User user = userBO.getUser(borrow.getApplyUser());
        // 状态检验
        if (!EBorrowStatus.TO_APPROVE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn623000", "该申请记录不处于待审核状态");
        }
        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EBorrowStatus.APPROVE_YES.getCode();
            // 通知放款人
            List<Noticer> noticers = noticerBO.queryNoticersNow(
                ENoticerType.Loaner.getCode(), borrow.getCompanyCode());
            if (!noticers.isEmpty()) {
                for (Noticer noticer : noticers) {
                    smsOutBO.sendContent(noticer.getMobile(),
                        "借款订单" + borrow.getCode() + "已审核通过，请尽早在管理端给该订单放款",
                        borrow.getCompanyCode(), ESystemCode.YLQ.getCode());
                }
            }
        } else {
            status = EBorrowStatus.APPROVE_NO.getCode();
            // 使用优惠券
            userCouponBO.useCancel(borrow.getCode());
            // 返还额度信用分
            certificationBO.refreshSxAmount(borrow.getApplyUser(),
                borrow.getAmount());
            smsOutBO.sendContent(
                user.getMobile(),
                "很抱歉，您的"
                        + CalculationUtil
                            .diviUp(borrow.getAmount().longValue())
                        + "借款（合同编号为" + borrow.getCode() + ")额度使用受限，原因："
                        + approveNote + "，详情请咨询客服。", borrow.getCompanyCode(),
                ESystemCode.YLQ.getCode());
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
            // User user = userBO.getUser(borrow.getApplyUser());
            // // 首次借款成功推荐人发放优惠券
            // BorrowOrder condition = new BorrowOrder();
            // condition.setApplyUser(borrow.getApplyUser());
            // condition.setStatus(EBorrowStatus.REPAY.getCode());
            // if (borrowOrderBO.getTotalCount(condition) == 1) {
            // if (StringUtils.isNotBlank(user.getUserReferee())) {
            // // couponConditionAO.recommendSuccess(user.getUserReferee());
            // }
            // }
            // 短信通知
            smsContent = "恭喜您，您的"
                    + CalculationUtil.diviUp(borrow.getAmount().longValue())
                    + "借款已经成功放款，合同编号为" + borrow.getCode() + "，详情查看请登录APP。";
        } else {
            borrowOrderBO.loanFailure(borrow, updater, remark);
            smsContent = "很抱歉，您的"
                    + CalculationUtil.diviUp(borrow.getAmount().longValue())
                    + "借款(合同编号:" + borrow.getCode() + ")放款失败，原因为：" + remark
                    + "，详情查看请登录APP。";
        }
        if (StringUtils.isNotBlank(smsContent)) {
            User user = userBO.getUser(borrow.getApplyUser());
            smsOutBO.sendContent(user.getMobile(), smsContent,
                borrow.getCompanyCode(), ESystemCode.YLQ.getCode());
        }
    }

    @Override
    @Transactional
    public void resubmitLoan(String code) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        User user = userBO.getUser(borrow.getApplyUser());
        if (!EBorrowStatus.LOAN_NO.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于打款失败状态，不能重新提交");
        }
        borrowOrderBO.resubmitLoan(borrow);
        // 放款通知人
        List<Noticer> noticers = noticerBO.queryNoticersNow(
            ENoticerType.Loaner.getCode(), borrow.getCompanyCode());
        String sendContent = "用户：" + user.getMobile() + "有一笔借款等待放款，请尽早在管理端进行放款";
        if (!noticers.isEmpty()) {
            for (Noticer noticer : noticers) {
                smsOutBO.sendContent(noticer.getMobile(), sendContent,
                    borrow.getCompanyCode(), ESystemCode.YLQ.getCode());
            }
        }
    }

    @Override
    public Object repay(String code) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(code);
        if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                && !EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn6230000", "借款不处于待还款状态");
        }
        return doRepayOffline(borrow);
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
        repayApply.setCompanyCode(borrow.getCompanyCode());
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
            // 额度重置为0
            certificationBO.resetSxAmount(borrow.getApplyUser());
            // 借还成功发放优惠券
            // couponConditionAO.repaySuccess(borrow.getApplyUser());
            // 发送短信
            User user = userBO.getUser(borrow.getApplyUser());
            smsOutBO
                .sendContent(
                    user.getMobile(),
                    "您的"
                            + CalculationUtil.diviUp(borrow.getAmount()
                                .longValue()) + "借款（合同编号：" + borrow.getCode()
                            + "）已经成功还款，详情查看请登录APP。", borrow.getCompanyCode(),
                    ESystemCode.YLQ.getCode());
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
        User user = userBO.getUser(userId);
        Company company = companyBO.getCompany(borrow.getCompanyCode());
        StringBuffer sb = new StringBuffer();
        if (null != user.getIdNo()) {
            sb = new StringBuffer(user.getIdNo());
        }
        String contentTemplate = sysConfigBO.getStringValue(
            SysConstants.SMS_CUISHOU, ESystemCode.YLQ.getCode());
        contentTemplate = String.format(contentTemplate, user.getMobile(), sb
            .replace(8, 11, "****").toString(), company.getName(), user
            .getMobile());
        // 向本人发送催收短信
        smsOutBO.sendContent(user.getMobile(), contentTemplate,
            borrow.getCompanyCode(), ESystemCode.YLQ.getCode());

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
        // 额度重置为0
        certificationBO.resetSxAmount(borrow.getApplyUser());
        User user = userBO.getUser(borrow.getApplyUser());
        // 将用户拉入黑名单
        userBO.refereshBlack(user);

    }

    @Override
    public void doCheckOverdueDaily() {
        logger.info("***************开始扫描逾期借款***************");
        BorrowOrder condition = new BorrowOrder();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        statusList.add(EBorrowStatus.OVERDUE.getCode());
        condition.setStatusList(statusList);
        condition.setIsStage(EBoolean.NO.getCode());
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

    private void overdue(BorrowOrder borrow) {
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
        BigDecimal yqlxFdRate = BigDecimal.valueOf(sysConfigBO.getDoubleValue(
            SysConstants.YQLX_FD_RATE, borrow.getCompanyCode()));
        BigDecimal fdAmount = borrow.getAmount().multiply(yqlxFdRate);
        if (yqlxAmount.compareTo(fdAmount) > 0) {
            yqlxAmount = fdAmount;
        }
        borrow.setYqDays(yqDays);
        borrow.setYqlxAmount(yqlxAmount);
        borrow.setTotalAmount(borrow.getAmount().add(yqlxAmount));
        borrow.setStatus(EBorrowStatus.OVERDUE.getCode());
        borrow.setUpdater("程序自动更新");
        borrow.setUpdateDatetime(new Date());
        borrow.setRemark("已逾期");
        borrowOrderBO.overdue(borrow);

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
        logger.info("***************开始扫描明日到期借款和分期，短信提醒***************");
        // 借款记录
        BorrowOrder condition = new BorrowOrder();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        condition.setStatusList(statusList);
        condition.setHkDatetime(DateUtil.getRelativeDateOfDays(
            DateUtil.getTodayEnd(), 1));
        condition.setIsStage(EBoolean.NO.getCode());
        List<BorrowOrder> borrowList = borrowOrderBO.queryBorrowList(condition);
        // 分期记录
        Staging conditionStaging = new Staging();
        conditionStaging.setStatus(EStagingStatus.TOREPAY.getCode());
        conditionStaging.setLastPayDate(DateUtil.getRelativeDateOfDays(
            DateUtil.getTodayEnd(), 1));
        List<Staging> stagingList = stagingBO
            .queryStagingList(conditionStaging);
        if (borrowList != null || stagingList != null) {
            logger.info("***************共扫描到"
                    + (borrowList.size() + stagingList.size())
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (BorrowOrder borrow : borrowList) {
                User user = userBO.getUser(borrow.getApplyUser());
                smsOutBO.sendContent(
                    user.getMobile(),
                    "您的"
                            + CalculationUtil.diviUp(borrow.getAmount()
                                .longValue()) + "借款即将到期，借款编号为"
                            + borrow.getCode()
                            + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。", borrow
                        .getCompanyCode(), ESystemCode.YLQ.getCode());
            }
        }
        if (CollectionUtils.isNotEmpty(stagingList)) {
            for (Staging staging : stagingList) {
                User user = userBO.getUser(staging.getApplyUser());
                smsOutBO.sendContent(user.getMobile(), "您的分期借款即将到期，分期编号为"
                        + staging.getCode()
                        + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。",
                    staging.getCompanyCode(), ESystemCode.YLQ.getCode());
            }
        }
        logger.info("***************结束扫描明日到期借款和分期***************");
    }

    public void doCheckStageDaily() {
        logger.info("***************开始扫描分期记录***************");
        Staging condition = new Staging();
        condition.setStatus(EStagingStatus.TOREPAY.getCode());
        condition.setCurDatetime(new Date());
        List<Staging> stagingList = stagingBO.queryStagingList(condition);

        if (CollectionUtils.isNotEmpty(stagingList)) {
            logger.info("***************扫描到" + stagingList.size()
                    + "条分期记录***************");
            for (Staging staging : stagingList) {
                stageOverdue(staging);
            }
        }

        logger.info("***************结束扫描分期记录***************");
    }

    public void doCheckWillRepayAt8() {
        logger.info("***************开始扫描今日到期借款和分期，短信提醒***************");
        // 借款记录
        BorrowOrder condition = new BorrowOrder();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        condition.setStatusList(statusList);
        condition.setHkDatetime(DateUtil.getTodayEnd());
        condition.setIsStage(EBoolean.NO.getCode());
        List<BorrowOrder> borrowList = borrowOrderBO.queryBorrowList(condition);
        // 分期记录
        Staging conditionStaging = new Staging();
        conditionStaging.setStatus(EStagingStatus.TOREPAY.getCode());
        conditionStaging.setLastPayDate(DateUtil.getTodayEnd());
        List<Staging> stagingList = stagingBO
            .queryStagingList(conditionStaging);
        if (borrowList != null || stagingList != null) {
            logger.info("***************共扫描到"
                    + (borrowList.size() + stagingList.size())
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (BorrowOrder borrow : borrowList) {
                User user = userBO.getUser(borrow.getApplyUser());
                smsOutBO.sendContent(
                    user.getMobile(),
                    "您的"
                            + CalculationUtil.diviUp(borrow.getAmount()
                                .longValue()) + "借款即将到期，借款编号为"
                            + borrow.getCode()
                            + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。", borrow
                        .getCompanyCode(), ESystemCode.YLQ.getCode());
            }
        }
        if (CollectionUtils.isNotEmpty(stagingList)) {
            for (Staging staging : stagingList) {
                User user = userBO.getUser(staging.getApplyUser());
                smsOutBO.sendContent(user.getMobile(), "您的分期借款即将到期，分期编号为"
                        + staging.getCode()
                        + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。",
                    staging.getCompanyCode(), ESystemCode.YLQ.getCode());
            }
        }
        logger.info("***************结束扫描今日到期借款和分期***************");
    }

    public void doCheckWillRepayAt18() {
        logger.info("***************开始扫描今日到期借款和分期，短信提醒***************");
        // 借款记录
        BorrowOrder condition = new BorrowOrder();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        condition.setStatusList(statusList);
        condition.setHkDatetime(DateUtil.getTodayEnd());
        condition.setIsStage(EBoolean.NO.getCode());
        List<BorrowOrder> borrowList = borrowOrderBO.queryBorrowList(condition);
        // 分期记录
        Staging conditionStaging = new Staging();
        conditionStaging.setStatus(EStagingStatus.TOREPAY.getCode());
        conditionStaging.setLastPayDate(DateUtil.getTodayEnd());
        List<Staging> stagingList = stagingBO
            .queryStagingList(conditionStaging);
        if (borrowList != null || stagingList != null) {
            logger.info("***************共扫描到"
                    + (borrowList.size() + stagingList.size())
                    + "条记录***************");
        }
        if (CollectionUtils.isNotEmpty(borrowList)) {
            for (BorrowOrder borrow : borrowList) {
                User user = userBO.getUser(borrow.getApplyUser());
                smsOutBO.sendContent(
                    user.getMobile(),
                    "您的"
                            + CalculationUtil.diviUp(borrow.getAmount()
                                .longValue()) + "借款即将到期，借款编号为"
                            + borrow.getCode()
                            + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。", borrow
                        .getCompanyCode(), ESystemCode.YLQ.getCode());
            }
        }
        if (CollectionUtils.isNotEmpty(stagingList)) {
            for (Staging staging : stagingList) {
                User user = userBO.getUser(staging.getApplyUser());
                smsOutBO.sendContent(user.getMobile(), "您的分期借款即将到期，分期编号为"
                        + staging.getCode()
                        + "，逾期将会影响您的信用并产生额外利息，请及时登录APP进行还款。",
                    staging.getCompanyCode(), ESystemCode.YLQ.getCode());
            }
        }
        logger.info("***************结束扫描今日到期借款和分期***************");
    }

    private void stageOverdue(Staging staging) {

        // 查询借款订单
        BorrowOrder borrow = borrowOrderBO.getBorrow(staging.getOrderCode());

        Staging condition = new Staging();
        condition.setStatus(EStagingStatus.TOREPAY.getCode());
        condition.setOrderCode(staging.getOrderCode());
        List<Staging> stagingList = stagingBO.queryStagingList(condition);

        // 此次分期应付利息
        BigDecimal lxAmount = staging.getRate().multiply(borrow.getAmount())
            .multiply(new BigDecimal(borrow.getStageCycle()));

        // 剩余未还总本金
        BigDecimal remainAmount = BigDecimal.ZERO;
        for (Staging data : stagingList) {
            // 分期记录设置成逾期
            stagingBO.refreshOverdue(data);
            // 分期应还本金累加
            remainAmount = remainAmount.add(data.getMainAmount());
        }

        // 本次逾期一天造成利息=7天内逾期利息*应还本息
        BigDecimal yqlxAmount = borrow.getRate1().multiply(
            remainAmount.add(lxAmount));

        // 更新借款订单
        borrow.setYqDays(1);
        borrow.setYqlxAmount(yqlxAmount);
        borrow.setTotalAmount(lxAmount.add(yqlxAmount).add(remainAmount));
        borrow.setAmount(lxAmount.add(remainAmount));
        borrow.setStatus(EBorrowStatus.OVERDUE.getCode());
        borrow.setUpdater("程序自动更新");
        borrow.setIsStage(EBoolean.NO.getCode());
        borrow.setUpdateDatetime(new Date());
        borrow.setRemark("已逾期");
        borrowOrderBO.overdue(borrow);
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

    @Override
    @Transactional
    public void nomalStaging(String ruleCode, String orderCode, String updater,
            String remark) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(orderCode);
        if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单不处于待还款状态，不可进行分期");
        }
        if (EBoolean.YES.getCode().equals(borrow.getIsStage())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单已分期，请勿重复分期");
        }
        List<RepayApply> result = repayApplyBO
            .queryCurrentRepayApplyList(borrow.getApplyUser());
        if (result.size() > 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该订单当前有一条待审核的还款申请，请先完成审批");
        }
        // 分期规则
        StagingRule rule = stagingRuleBO.getStagingRule(ruleCode);
        long count = rule.getCount(); // 期数
        long cycle = rule.getCycle(); // 周期
        BigDecimal rate = rule.getRate(); // 分期日利率
        for (int i = 0; i < count; i++) {
            // 本期分期开始时间
            Date startPayDate = DateUtil.getDaysStart(borrow.getHkDatetime(),
                (int) (i * cycle + 1));
            // 本期结束时间
            Date lastPayDate = DateUtil.getDaysEnd(borrow.getHkDatetime(),
                (int) ((i + 1) * cycle));
            // 每期本金
            BigDecimal mainAmount = borrow.getTotalAmount().divide(
                new BigDecimal(count), 0, BigDecimal.ROUND_UP);
            // 落地本期
            stagingBO.saveStaging(borrow.getApplyUser(), orderCode, mainAmount,
                rate, startPayDate, lastPayDate, (long) (i + 1),
                borrow.getStageBatch() + 1, borrow.getCompanyCode());
        }
        borrowOrderBO.refreshStaging(borrow, count, cycle, updater, remark);
        String content = "您的借款订单" + borrow.getCode() + "已成功分期，分为" + count
                + "期，每期" + cycle + "天，日利率：" + rate;
        User user = userBO.getUser(borrow.getApplyUser());
        smsOutBO.sendContent(user.getMobile(), content,
            borrow.getCompanyCode(), ESystemCode.YLQ.getCode());
    }

    @Override
    @Transactional
    public void yqStaging(String ruleCode, String orderCode, String updater,
            String remark) {
        BorrowOrder borrow = borrowOrderBO.getBorrow(orderCode);
        if (!EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("xn0000", "该订单状态不可逾期分期");
        }
        if (EBoolean.YES.getCode().equals(borrow.getIsStage())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "订单已分期，请勿重复分期");
        }
        List<RepayApply> result = repayApplyBO
            .queryCurrentRepayApplyList(borrow.getApplyUser());
        if (result.size() > 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "该订单当前有一条待审核的还款申请，请先完成审批");
        }
        StagingRule rule = stagingRuleBO.getStagingRule(ruleCode);
        long count = rule.getCount();
        long cycle = rule.getCycle();
        BigDecimal rate = rule.getRate();
        Date now = new Date();
        for (int i = 0; i < count; i++) {
            // 本期分期开始时间
            Date startPayDate = DateUtil.getDaysStart(now, (int) (i * cycle));
            // 本期结束时间
            Date lastPayDate = DateUtil.getDaysEnd(now,
                (int) ((i + 1) * cycle - 1));
            // 每期本金
            BigDecimal mainAmount = borrow.getTotalAmount()
                .subtract(borrow.getYqlxAmount())
                .divide(new BigDecimal(count), 0, BigDecimal.ROUND_UP);
            if (i == 0) {
                mainAmount = mainAmount.add(borrow.getYqlxAmount());
            }
            // 落地本期
            stagingBO.saveStaging(borrow.getApplyUser(), orderCode, mainAmount,
                rate, startPayDate, lastPayDate, (long) (i + 1),
                borrow.getStageBatch() + 1, borrow.getCompanyCode());
        }
        borrowOrderBO.refreshStaging(borrow, count, cycle, updater, remark);

        // 落地逾期记录
        overdueBO.saveOverdue(borrow.getApplyUser(), borrow.getCode(),
            borrow.getYqDays(), borrow.getYqlxAmount(),
            EOverdueDeal.STAGE.getCode());

        String content = "您的借款订单" + borrow.getCode() + "已成功分期，分为" + count
                + "期，每期" + cycle + "天，日利率：" + rate;
        User user = userBO.getUser(borrow.getApplyUser());
        smsOutBO.sendContent(user.getMobile(), content,
            borrow.getCompanyCode(), ESystemCode.YLQ.getCode());
    }

    @Override
    public List<StageInfo> calStage(String stageRuleCode, String orderCode) {
        StagingRule rule = stagingRuleBO.getStagingRule(stageRuleCode);
        BorrowOrder order = borrowOrderBO.getBorrow(orderCode);

        if (!EBorrowStatus.LOANING.getCode().equals(order.getStatus())
                && !EBorrowStatus.OVERDUE.getCode().equals(order.getStatus())
                && EBoolean.NO.getCode().equals(order.getIsStage())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "只有已放款和逾期订单可以分期");
        }
        Long count = rule.getCount();
        Long cycle = rule.getCycle();
        BigDecimal rate = rule.getRate();
        List<StageInfo> infos = new ArrayList<StageInfo>();
        if (EBorrowStatus.LOANING.getCode().equals(order.getStatus())) {
            for (int i = 0; i < count; i++) {
                // 本期分期开始时间
                Date startPayDate = DateUtil.getDaysStart(
                    order.getHkDatetime(), (int) (i * cycle + 1));
                // 每期本金
                BigDecimal mainAmount = order.getTotalAmount().divide(
                    new BigDecimal(count), 0, BigDecimal.ROUND_UP);
                for (int j = 1; j <= cycle; j++) {
                    StageInfo info = new StageInfo();
                    info.setDate(DateUtil.dateToStr(startPayDate,
                        DateUtil.FRONT_DATE_FORMAT_STRING));
                    info.setLxAmount(order.getTotalAmount().multiply(rate)
                        .multiply(new BigDecimal(j)));
                    info.setMainAmount(mainAmount);
                    info.setAmount(mainAmount.add(info.getLxAmount()));
                    info.setRemark("第" + (i + 1) + "期，第" + j + "天");
                    infos.add(info);
                    // 日期加一
                    startPayDate = DateUtil.getRelativeDateOfDays(startPayDate,
                        1);
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                // 本期分期开始时间
                Date startPayDate = DateUtil.getDaysStart(
                    order.getHkDatetime(), (int) (i * cycle + 1));
                // 每期本金
                BigDecimal mainAmount = order.getTotalAmount()
                    .subtract(order.getYqlxAmount())
                    .divide(new BigDecimal(count), 0, BigDecimal.ROUND_UP);
                if (i == 0) {
                    mainAmount = mainAmount.add(order.getYqlxAmount());
                }
                for (int j = 1; j <= cycle; j++) {
                    StageInfo info = new StageInfo();
                    info.setDate(DateUtil.dateToStr(startPayDate,
                        DateUtil.FRONT_DATE_FORMAT_STRING));
                    info.setLxAmount(order.getTotalAmount().multiply(rate)
                        .multiply(new BigDecimal(j)));
                    info.setMainAmount(mainAmount);
                    info.setAmount(mainAmount.add(info.getLxAmount()));
                    info.setRemark("第" + (i + 1) + "期，第" + j + "天");
                    infos.add(info);
                    // 日期加一
                    startPayDate = DateUtil.getRelativeDateOfDays(startPayDate,
                        1);
                }
            }
        }
        return infos;
    }

    @Override
    public Paginable<BorrowOrder> queryNearlyOrder(int start, int limit,
            String companyCode) {
        BorrowOrder condition = new BorrowOrder();
        condition.setCompanyCode(companyCode);
        condition.setStatus(EBorrowStatus.LOANING.getCode());
        condition.setIsStage(EBoolean.NO.getCode());
        List<BorrowOrder> result = borrowOrderBO.queryBorrowList(condition);
        List<BorrowOrder> list = new ArrayList<BorrowOrder>();
        for (BorrowOrder order : result) {
            if (DateUtil.daysBetween(new Date(), order.getHkDatetime()) <= 2) {
                list.add(order);
            }
        }
        Paginable<BorrowOrder> page = new Page<BorrowOrder>(start, limit,
            list.size());
        List<BorrowOrder> orders = new ArrayList<BorrowOrder>();
        for (int i = page.getStart(); i < list.size(); i++) {
            orders.add(list.get(i));
            if (orders.size() == page.getPageSize()) {
                break;
            }
        }
        page.setList(orders);
        return page;
    }

    @Override
    public void repayWarning(String code) {
        BorrowOrder order = borrowOrderBO.getBorrow(code);
        String content = "尊敬的用户，您的"
                + order.getAmount().divide(new BigDecimal(1000)).toString()
                + "借款还款时间截止到"
                + DateUtil.dateToStr(order.getHkDatetime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING) + "，请及时还款";
        User user = userBO.getUser(order.getApplyUser());
        smsOutBO.sendContent(user.getMobile(), content, order.getCompanyCode(),
            ESystemCode.YLQ.getCode());
    }

}
