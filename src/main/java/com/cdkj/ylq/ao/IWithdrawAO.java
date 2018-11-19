package com.cdkj.ylq.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Withdraw;
import com.cdkj.ylq.dto.res.XN629903Res;
import com.cdkj.ylq.spring.ServiceModule;

@ServiceModule
public interface IWithdrawAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 取现审核
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote);

    // 支付回录
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder, BigDecimal transFee);

    public void withdrawEnter(String accountNumber, BigDecimal amount,
            String withDate, String channelOrder, String withNote,
            String updater);

    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition);

    public List<Withdraw> queryWithdrawList(Withdraw condition);

    public Withdraw getWithdraw(String code);

    public BigDecimal getTotalWithdraw(String currency);

    public XN629903Res getTotalWithdraw(String applyUser,
            List<String> statusList);
}
