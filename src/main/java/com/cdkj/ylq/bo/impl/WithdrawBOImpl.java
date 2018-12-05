package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.IWithdrawBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.AmountUtil;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IWithdrawDAO;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.Withdraw;
import com.cdkj.ylq.enums.EChannelType;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.enums.EWithdrawStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

@Component
public class WithdrawBOImpl extends PaginableBOImpl<Withdraw> implements
        IWithdrawBO {

    @Autowired
    private IWithdrawDAO withdrawDAO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    private IBusinessManBO businessManBO;

    @Override
    public String applyOrder(Account account, BigDecimal amount,
            BigDecimal fee, String payCardInfo, String payCardNo,
            String applyUser, String applyUserType, String applyNote) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("xn000000", "取现金额不能为0");
        }
        String code = OrderNoGenerater.generateM("QX");
        Withdraw data = new Withdraw();
        data.setCode(code);
        data.setAccountNumber(account.getAccountNumber());
        data.setAccountType(account.getType());
        data.setCurrency(account.getCurrency());
        data.setAmount(amount);

        data.setFee(fee);
        data.setActualAmount(amount.subtract(fee));
        data.setChannelType(EChannelType.Offline.getCode());
        data.setChannelBank(account.getCurrency());
        data.setPayCardInfo(payCardInfo);

        data.setPayCardNo(payCardNo);
        data.setStatus(EWithdrawStatus.toApprove.getCode());
        data.setApplyUser(applyUser);
        data.setApplyUserType(applyUserType);
        data.setApplyNote(applyNote);
        data.setApplyDatetime(new Date());
        withdrawDAO.insert(data);
        return code;
    }

    @Override
    public void approveOrder(Withdraw data, EWithdrawStatus status,
            String approveUser, String approveNote) {
        data.setStatus(status.getCode());
        data.setApproveUser(approveUser);
        data.setApproveNote(approveNote);
        data.setApproveDatetime(new Date());
        withdrawDAO.approveOrder(data);

    }

    @Override
    public void payOrder(Withdraw data, EWithdrawStatus status, String payUser,
            String payNote, String channelOrder, BigDecimal payFee) {
        data.setStatus(status.getCode());
        data.setPayUser(payUser);
        data.setPayNote(payNote);
        data.setPayFee(payFee);
        data.setChannelOrder(channelOrder);
        data.setPayDatetime(new Date());
        withdrawDAO.payOrder(data);
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        return withdrawDAO.selectList(condition);
    }

    @Override
    public Withdraw getWithdraw(String code) {
        Withdraw data = null;
        if (StringUtils.isNotBlank(code)) {
            Withdraw condition = new Withdraw();
            condition.setCode(code);
            data = withdrawDAO.select(condition);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(), "取现订单"
                        + code + "不存在");
            }
        }
        return data;
    }

    @Override
    public BigDecimal doCheckAndGetFee(Account account, BigDecimal amount) {
        if (!ECurrency.CNY.getCode().equals(account.getCurrency())) {
            throw new BizException("xn0000", "币种不支持");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException("xn000000", "提现金额需大于零");
        }
        Withdraw condition = new Withdraw();
        condition.setAccountNumber(account.getAccountNumber());
        condition.setStatus("13");// 待申请，审核成功待支付
        if (withdrawDAO.selectTotalCount(condition) > 0) {
            throw new BizException("xn000000", "上笔取现申请还未处理成功，不能再次申请");
        }

        Map<String, String> argsMap = sysConfigBO.getConfigsMap(null,
            ESystemCode.YLQ.getCode());
        String qxbs = SysConstants.USERQXBS; // 取现倍数
        String qxfl = SysConstants.USERQXFL; // 取现手续费率
        String monthTimesKey = SysConstants.USERMONTIMES;// 本月申请次数是否达到上限
        String qxDbzdjeValue = argsMap.get(SysConstants.QXDBZDJE);// 取现单笔最大金额

        String monthTimesValue = argsMap.get(monthTimesKey);
        if (StringUtils.isNotBlank(monthTimesValue)) {// 月取现次数判断
            Withdraw cond = new Withdraw();
            cond.setAccountNumber(account.getAccountNumber());
            cond.setApplyDatetimeStart(DateUtil.getCurrentMonthFirstDay());
            cond.setApplyDatetimeEnd(DateUtil.getCurrentMonthLastDay());
            long totalCount = withdrawDAO.selectTotalCount(cond);
            long maxMonthTimes = Long.valueOf(monthTimesValue);
            if (totalCount >= maxMonthTimes) {
                throw new BizException("xn0000", "每月取现最多" + maxMonthTimes
                        + "次,本月申请次数已用尽");
            }
        }

        if (StringUtils.isNotBlank(qxDbzdjeValue)) {
            BigDecimal qxDbzdje = AmountUtil.mul(new BigDecimal(qxDbzdjeValue),
                1000L);
            if (amount.compareTo(qxDbzdje) > 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "取现单笔最大金额不能超过" + qxDbzdjeValue + "元。");
            }
        }

        String qxBsValue = argsMap.get(qxbs);
        if (StringUtils.isNotBlank(qxBsValue)) {
            // 取现金额倍数
            BigDecimal qxBs = AmountUtil.mul(new BigDecimal(qxBsValue), 1000L);
            if (qxBs.compareTo(BigDecimal.ZERO) > 0
                    && amount.divideAndRemainder(qxBs)[1]
                        .compareTo(BigDecimal.ZERO) > 0) {
                throw new BizException("xn000000", "金额请取" + qxBsValue + "的倍数");
            }
        }

        double feeRate = 0;
        String feeRateValue = argsMap.get(qxfl);
        if (StringUtils.isNotBlank(feeRateValue)) {
            feeRate = Double.valueOf(feeRateValue);
        }
        return AmountUtil.mul(amount, feeRate);
    }

    @Override
    public Withdraw getWithdrawByChannelOrder(String hash) {
        Withdraw withdraw = null;
        Withdraw condition = new Withdraw();
        condition.setChannelOrder(hash);
        List<Withdraw> results = withdrawDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            withdraw = results.get(0);
        }
        return withdraw;
    }

    @Override
    public BigDecimal getTotalWithdraw(String currency) {
        Withdraw condition = new Withdraw();
        condition.setCurrency(currency);
        condition.setStatus(EWithdrawStatus.Pay_YES.getCode());
        return withdrawDAO.selectTotalAmount(condition);
    }

    @Override
    public BigDecimal getTotalWithdraw(String applyUser, List<String> statusList) {
        Withdraw condition = new Withdraw();
        condition.setApplyUser(applyUser);
        condition.setStatusList(statusList);
        return withdrawDAO.selectTotalAmount(condition);
    }
}
