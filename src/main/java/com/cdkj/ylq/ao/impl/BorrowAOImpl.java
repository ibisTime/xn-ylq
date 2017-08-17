package com.cdkj.ylq.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IBorrowAO;
import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.AmountUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;

@Service
public class BorrowAOImpl implements IBorrowAO {

    @Autowired
    private IBorrowBO borrowBO;

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private IUserCouponBO userCouponBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IUserBO userBO;

    @Override
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
        // 是否已经有借款
        Borrow condition = new Borrow();
        condition.setApplyUser(userId);
        if (borrowBO.getTotalCount(condition) > 0) {
            throw new BizException("623070", "当前已有借款");
        }
        // 产品
        Product product = productBO.getProduct(certification.getRef());
        // 查询优惠券
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
        Long borrowAmount = infoAmount.getSxAmount();
        Borrow borrow = new Borrow();

        borrow.setCode(code);
        borrow.setApplyUser(userId);
        borrow.setSignDatetime(now);
        borrow.setAmount(borrowAmount);
        borrow.setDuration(product.getDuration());

        borrow.setLxAmount(AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getLxRate())));
        borrow.setXsAmount(AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getXsRate())));
        borrow.setGlAmount(AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getGlRate())));
        borrow.setFwAmount(AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getFwRate())));
        borrow.setYhAmount(yhAmount);

        borrow.setRate1(product.getYqRate1());
        borrow.setRate2(product.getYqRate2());
        borrow.setYqlxAmount(0L);
        borrow.setYqDays(0);
        borrow.setStatus(EBorrowStatus.TO_LOAN.getCode());

        borrow.setUpdater(userId);
        borrow.setUpdateDatetime(now);
        borrow.setRemark("新申请借款");

        borrowBO.saveBorrow(borrow);
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
}
