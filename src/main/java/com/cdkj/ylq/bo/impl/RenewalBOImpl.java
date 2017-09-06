package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IRenewalBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.AmountUtil;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IRenewalDAO;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Renewal;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.ERenewalStatus;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.exception.BizException;

@Component
public class RenewalBOImpl extends PaginableBOImpl<Renewal> implements
        IRenewalBO {

    @Autowired
    private IRenewalDAO renewalDAO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public Renewal applyRenewal(Borrow borrow) {
        Renewal renewal = new Renewal();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.RENEWAL
            .getCode());
        String payGroup = OrderNoGenerater.generateM(EGeneratePrefix.PAY_GROUP
            .getCode());
        Integer step = sysConfigBO.getIntegerValue(SysConstants.RENEWAL_STEP,
            ESystemCode.YLQ.getCode(), ESystemCode.YLQ.getCode());
        if (step <= 0) {
            throw new BizException("xn623000", "平台暂未开放续期功能，敬请期待！");
        }
        Integer cycle = 1;

        Date now = new Date();
        Date startDate = null;
        if (now.after(borrow.getHkDatetime())) {
            startDate = DateUtil.getTomorrowStart(now);

        } else {
            startDate = DateUtil.getTomorrowStart(borrow.getHkDatetime());
        }
        Date endDate = DateUtil.getRelativeDate(startDate, step * cycle * 24
                * 3600 - 1);

        // 借款总额
        Long borrowAmount = borrow.getAmount();
        // 利息
        Long lxAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            borrow.getLxRate())) * step * cycle;
        // 快速信审费
        Long xsAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            borrow.getXsRate())) * step * cycle;
        // 账户管理费
        Long glAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            borrow.getGlRate())) * step * cycle;
        // 服务费
        Long fwAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            borrow.getFwRate())) * step * cycle;
        // 续期总金额
        Long totalAmount = borrow.getYqlxAmount() + lxAmount + xsAmount
                + glAmount + fwAmount;

        renewal.setCode(code);
        renewal.setApplyUser(borrow.getApplyUser());
        renewal.setBorrowCode(borrow.getCode());
        renewal.setStep(step);
        renewal.setCycle(cycle);

        renewal.setStartDate(startDate);
        renewal.setEndDate(endDate);
        renewal.setYqAmount(borrow.getYqlxAmount());
        renewal.setXsAmount(xsAmount);
        renewal.setGlAmount(glAmount);

        renewal.setFwAmount(fwAmount);
        renewal.setLxAmount(lxAmount);
        renewal.setTotalAmount(totalAmount);
        renewal.setCreateDatetime(now);
        renewal.setPayGroup(payGroup);

        renewal.setStatus(ERenewalStatus.TO_PAY.getCode());

        renewalDAO.insert(renewal);
        return renewal;
    }

    @Override
    public List<Renewal> queryRenewalList(Renewal condition) {
        return renewalDAO.selectList(condition);
    }

    @Override
    public List<Renewal> queryRenewalListByPayGroup(String payGroup) {
        Renewal condition = new Renewal();
        condition.setPayGroup(payGroup);
        return renewalDAO.selectList(condition);
    }

    @Override
    public Renewal getRenewal(String code) {
        Renewal data = null;
        if (StringUtils.isNotBlank(code)) {
            Renewal condition = new Renewal();
            condition.setCode(code);
            data = renewalDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "记录不存在");
            }
        }
        return data;
    }

    @Override
    public int renewalSuccess(Renewal renewal, String payCode, String payType,
            Integer curNo) {
        int count = 0;
        if (renewal != null) {
            renewal.setPayDatetime(new Date());
            renewal.setPayCode(payCode);
            renewal.setPayType(payType);
            renewal.setStatus(ERenewalStatus.PAY_YES.getCode());
            renewal.setCurNo(curNo);
            renewalDAO.updateRenewalSuccess(renewal);
        }
        return count;
    }
}
