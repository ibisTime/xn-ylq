package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.Withdraw;
import com.cdkj.ylq.enums.EWithdrawStatus;

public interface IWithdrawBO extends IPaginableBO<Withdraw> {

    String applyOrder(Account account, BigDecimal amount, BigDecimal fee,
            String payCardInfo, String payCardNo, String applyUser,
            String applyUserType, String applyNote);

    BigDecimal doCheckAndGetFee(Account account, BigDecimal amount);

    void approveOrder(Withdraw data, EWithdrawStatus status,
            String approveUser, String approveNote);

    void payOrder(Withdraw data, EWithdrawStatus status, String payUser,
            String payNote, String channelOrder, BigDecimal payFee);

    List<Withdraw> queryWithdrawList(Withdraw condition);

    Withdraw getWithdraw(String code);

    public Withdraw getWithdrawByChannelOrder(String hash);

    public BigDecimal getTotalWithdraw(String currency);

    public BigDecimal getTotalWithdraw(String applyUser, List<String> statusList);
}
