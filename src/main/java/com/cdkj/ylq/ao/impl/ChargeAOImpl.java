package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IChargeAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IChargeBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.Charge;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EChannelType;
import com.cdkj.ylq.enums.EChargeStatus;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.enums.EJourBizTypePlat;
import com.cdkj.ylq.enums.EJourBizTypeUser;
import com.cdkj.ylq.enums.EPayType;
import com.cdkj.ylq.enums.ESystemAccount;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

@Service
public class ChargeAOImpl implements IChargeAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public String applyOrderOnline(String userId, String payType,
            BigDecimal transAmount) {
        User user = userBO.getUser(userId);
        String result = null;
        if (EPayType.ALIPAY.getCode().equals(payType)) {
            // result = alipayBO.getSignedOrder(user.getUserId(),
            // user.getUserId(), EJourBizTypeUser.CHARGE.getCode(),
            // EJourBizTypeUser.CHARGE.getCode(),
            // EJourBizTypeUser.CHARGE.getValue(), transAmount);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持支付方式");
        }
        return result;
    }

    @Override
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyUserType, String applyNote) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "充值金额需大于零");
        }
        Account account = accountBO.getAccount(accountNumber);
        // 生成充值订单
        String code = chargeBO.applyOrderOffline(account,
            EJourBizTypeUser.CHARGE.getCode(), amount, payCardInfo, payCardNo,
            applyUser, applyUserType, applyNote);
        return code;
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote) {
        Charge data = chargeBO.getCharge(code);
        if (!EChargeStatus.toPay.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote);
        } else {
            payOrderNO(data, payUser, payNote);
        }
    }

    private void payOrderNO(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, false, payUser, payNote);
    }

    private void payOrderYES(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, true, payUser, payNote);

        // 账户加钱
        Account accountUser = accountBO.getAccount(data.getAccountNumber());
        accountBO.changeAmount(accountUser, data.getAmount(),
            EChannelType.Offline, null, data.getCode(),
            EJourBizTypeUser.CHARGE.getCode(), "线下充值");

        Account account = accountBO.getAccount(data.getAccountNumber());
        if (ECurrency.CNY.getCode().equals(account.getCurrency())) {
            // 线下托管账户加钱
            Account sysAccountOffLine = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_OFFLINE.getCode());
            accountBO.changeAmount(sysAccountOffLine, data.getAmount(),
                EChannelType.Offline, null, data.getCode(),
                EJourBizTypePlat.CHARGE.getCode(), "线下充值");
        }
    }

    // 手动增发
    @Override
    @Transactional
    public void addSysAccount(BigDecimal amount, String currency,
            String bizNote, String updater) {
        // if (ECurrency.JF.getCode().equals(currency)) {
        // Account account = accountBO
        // .getAccount(ESystemAccount.SYS_ACOUNT_JF_POOL.getCode());
        // if (amount.add(account.getAmount()).compareTo(BigDecimal.ZERO) == -1)
        // {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
        // "余额不能小于0，请重新输入");
        // }
        //
        // accountBO.changeAmount(account, amount, EChannelType.Offline,
        // EJourBizTypePlat.HAND_CHARGE.getCode(),
        // EJourBizTypePlat.HAND_CHARGE.getCode(),
        // EJourBizTypePlat.HAND_CHARGE.getCode(), bizNote);
        // } else if (ECurrency.TPP.getCode().equals(currency)) {
        // Account account = accountBO
        // .getAccount(ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode());
        // if (amount.add(account.getAmount()).compareTo(BigDecimal.ZERO) == -1)
        // {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
        // "余额不能小于0，请重新输入");
        // }
        //
        // accountBO.changeAmount(account, amount, EChannelType.Offline,
        // EJourBizTypePlat.HAND_CHARGE.getCode(),
        // EJourBizTypePlat.HAND_CHARGE.getCode(),
        // EJourBizTypePlat.HAND_CHARGE.getCode(), bizNote);
        // }

    }

    @Override
    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition) {
        Paginable<Charge> page = chargeBO.getPaginable(start, limit, condition);
        List<Charge> chargeList = page.getList();
        for (Charge charge : chargeList) {
            initCharge(charge);
        }
        return page;
    }

    @Override
    public List<Charge> queryChargeList(Charge condition) {
        List<Charge> list = chargeBO.queryChargeList(condition);
        for (Charge charge : list) {
            initCharge(charge);
        }
        return list;
    }

    @Override
    public Charge getCharge(String code) {
        Charge charge = chargeBO.getCharge(code);
        initCharge(charge);
        return charge;
    }

    private void initCharge(Charge charge) {
        // 户名
        String realName = null;

        // 审核人
        String payUserName = null;

        // 账户
        Account account = accountBO.getAccount(charge.getAccountNumber());

        // 其他用户
        SYSUser sysUser = sysUserBO.getSYSUser(account.getUserId());

        realName = sysUser.getMobile();
        if (StringUtils.isNotBlank(sysUser.getRealName())) {
            realName = sysUser.getRealName().concat("-").concat(realName);
        }

        SYSUser payUser = sysUserBO.getSYSUserUnCheck(charge.getPayUser());
        if (null != payUser) {
            payUserName = payUser.getLoginName();
            if (StringUtils.isNotBlank(payUser.getMobile())) {
                payUserName = payUserName.concat("-").concat(
                    payUser.getMobile());
            }
        }

        charge.setRealName(realName);

        charge.setApplyUserName(realName);

        charge.setPayUserName(payUserName);
    }
}
