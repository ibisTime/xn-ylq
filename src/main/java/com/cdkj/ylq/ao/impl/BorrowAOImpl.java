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
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.AmountUtil;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBizType;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EPayType;
import com.cdkj.ylq.enums.ESysUser;
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

    @Override
    @Transactional
    public String borrow(String userId, Long couponId) {
        // 授信额度信息校验
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_AMOUNT.getCode());
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
        }

        String code = OrderNoGenerater.generateM(EGeneratePrefix.BORROW
            .getCode());
        Date now = new Date();
        // 借款总额
        Long borrowAmount = infoAmount.getSxAmount();
        // 利息
        Long lxAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getLxRate()));
        // 快速信审费
        Long xsAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getXsRate()));
        // 账户管理费
        Long glAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getGlRate()));
        // 服务费
        Long fwAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getFwRate()));
        // 应还金额
        Long totalAmount = borrowAmount;

        Borrow borrow = new Borrow();

        borrow.setCode(code);
        borrow.setApplyUser(userId);
        borrow.setSignDatetime(now);
        borrow.setAmount(borrowAmount);
        borrow.setLevel(product.getLevel());
        borrow.setDuration(product.getDuration());

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
        borrow.setStatus(EBorrowStatus.TO_LOAN.getCode());
        borrow.setUpdater(userId);
        borrow.setUpdateDatetime(now);
        borrow.setRemark("新申请借款");

        borrowBO.saveBorrow(borrow);

        Apply apply = applyBO.getCurrentApply(userId);
        apply.setStatus(EApplyStatus.TO_LOAN.getCode());
        applyBO.refreshStatus(apply);
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
        }
        return results;
    }

    @Override
    public Paginable<Borrow> queryMyBorrowPage(int start, int limit,
            Borrow condition) {
        return borrowBO.getPaginable(start, limit, condition);
    }

    @Override
    public Borrow getBorrow(String code) {
        Borrow borrow = borrowBO.getBorrow(code);
        borrow.setUser(userBO.getRemoteUser(borrow.getApplyUser()));
        return borrow;
    }

    @Override
    @Transactional
    public void loan(String code, String updater, String remark) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.TO_LOAN.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于待放款状态");
        }
        Date now = new Date();
        Date fkDatetime = now;
        Date jxDatetime = DateUtil.getTomorrowStart(fkDatetime);
        Date hkDatetime = DateUtil.getRelativeDateOfDays(jxDatetime,
            borrow.getDuration());
        borrow.setFkDatetime(fkDatetime);
        borrow.setJxDatetime(jxDatetime);
        borrow.setHkDatetime(hkDatetime);
        borrow.setStatus(EBorrowStatus.LOANING.getCode());
        borrow.setUpdater(updater);
        borrow.setUpdateDatetime(now);
        borrow.setRemark(remark);
        borrowBO.loan(borrow);

        Apply apply = applyBO.getCurrentApply(borrow.getApplyUser());
        apply.setStatus(EApplyStatus.LOANING.getCode());
        applyBO.refreshStatus(apply);
    }

    @Override
    public Object repay(String code, String payType) {
        Borrow borrow = borrowBO.getBorrow(code);
        if (!EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())
                && !EBorrowStatus.OVERDUE.getCode().equals(borrow.getStatus())) {
            throw new BizException("623071", "借款不处于待还款状态");
        }
        if (EPayType.ALIPAY.getCode().equals(payType)) {
            return doRepayAlipay(borrow);
        } else if (EPayType.WEIXIN_APP.getCode().equals(payType)) {
            return doRepayWechat(borrow);
        } else {
            throw new BizException("623071", "暂不支持此支付方式");
        }
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
        if (EBorrowStatus.LOANING.getCode().equals(borrow.getStatus())) {
            // 更新订单支付金额
            borrowBO.repaySuccess(borrow, amount, payCode, payType);
            // 更新申请单状态
            Apply apply = applyBO.getCurrentApply(borrow.getApplyUser());
            apply.setStatus(EApplyStatus.REPAY.getCode());
            applyBO.refreshStatus(apply);
            userId = borrow.getApplyUser();
        } else {
            logger.info("订单号：" + borrow.getCode() + "已支付，重复回调");
        }
        return userId;
    }

    @Override
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

        Apply apply = applyBO.getCurrentApply(borrow.getApplyUser());
        apply.setStatus(EApplyStatus.BAD.getCode());
        applyBO.refreshStatus(apply);

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
}
