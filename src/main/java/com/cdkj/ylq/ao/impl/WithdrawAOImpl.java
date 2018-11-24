package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IWithdrawAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IWithdrawBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.domain.Withdraw;
import com.cdkj.ylq.dto.res.XN629903Res;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EChannelType;
import com.cdkj.ylq.enums.EJourBizTypeBoss;
import com.cdkj.ylq.enums.EJourBizTypePlat;
import com.cdkj.ylq.enums.EWithdrawStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

@Service
public class WithdrawAOImpl implements IWithdrawAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Override
    @Transactional
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.toApprove.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "申请记录状态不是待审批状态，无法审批");
        }

        if (EBoolean.YES.getCode().equals(approveResult)) {
            approveOrderYES(data, approveUser, approveNote);
        } else {
            approveOrderNO(data, approveUser, approveNote);
        }
    }

    private void approveOrderYES(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_YES,
            approveUser, approveNote);
    }

    private void approveOrderNO(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_NO, approveUser,
            approveNote);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());

        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeBoss.WITHDRAW_UNFROZEN.getCode(), "取现失败退回",
            data.getCode());
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder, BigDecimal payFee) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.Approved_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            if (StringUtils.isBlank(channelOrder) || null == payFee) {
                throw new BizException("xn000000", "请填写渠道单号和转账费");
            }
        }

        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote, channelOrder, payFee);
        } else {
            payOrderNO(data, payUser, payNote, channelOrder);
        }
    }

    private void payOrderNO(Withdraw data, String payUser, String payNote,
            String payCode) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_NO, payUser, payNote,
            payCode, BigDecimal.ZERO);

        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeBoss.WITHDRAW_UNFROZEN.getCode(), "取现失败退回",
            data.getCode());
    }

    private void payOrderYES(Withdraw data, String payUser, String payNote,
            String payCode, BigDecimal payFee) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_YES, payUser, payNote,
            payCode, payFee);

        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 先解冻，然后扣减余额
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeBoss.WITHDRAW_UNFROZEN.getCode(),
            EJourBizTypeBoss.WITHDRAW_UNFROZEN.getValue(), data.getCode());

        // 用户账户取现并扣除手续费
        accountBO.changeAmount(dbAccount, data.getAmount().negate(),
            EChannelType.Offline, payCode, data.getCode(),
            EJourBizTypeBoss.WITHDRAW.getCode(), "取现成功");

        accountBO.changeAmount(dbAccount, data.getFee().negate(),
            EChannelType.Offline, payCode, data.getCode(),
            EJourBizTypePlat.WITHDRAW_FEE.getCode(), "取现手续费");

    }

    @Override
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition) {
        Paginable<Withdraw> page = withdrawBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (Withdraw withdraw : page.getList()) {
                initWithdraw(withdraw);
            }
        }

        return page;
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        List<Withdraw> list = withdrawBO.queryWithdrawList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (Withdraw withdraw : list) {
                initWithdraw(withdraw);
            }
        }
        return list;
    }

    @Override
    public Withdraw getWithdraw(String code) {
        Withdraw withdraw = withdrawBO.getWithdraw(code);

        initWithdraw(withdraw);

        return withdraw;
    }

    @Override
    public BigDecimal getTotalWithdraw(String currency) {
        return withdrawBO.getTotalWithdraw(currency);
    }

    @Override
    public XN629903Res getTotalWithdraw(String applyUser,
            List<String> statusList) {
        return new XN629903Res(withdrawBO.getTotalWithdraw(applyUser,
            statusList));
    }

    private void initWithdraw(Withdraw withdraw) {

        // 户名
        String realName = null;

        // 其他用户
        SYSUser sysUser = sysUserBO.getSYSUser(withdraw.getApplyUser());
        User user = new User();
        user.setMobile(sysUser.getMobile());
        withdraw.setUser(user);

        realName = sysUser.getMobile();
        if (StringUtils.isNotBlank(sysUser.getRealName())) {
            realName = sysUser.getRealName().concat("-").concat(realName);
        }

        withdraw.setRealName(realName);
    }

}
