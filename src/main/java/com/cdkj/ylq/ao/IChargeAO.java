package com.cdkj.ylq.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Charge;

public interface IChargeAO {

    String DEFAULT_ORDER_COLUMN = "code";

    public String applyOrderOnline(String userId, String payType,
            BigDecimal transAmount);

    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyUserType, String applyNote);

    public void payOrder(String code, String payUser, String payResult,
            String payNote);

    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition);

    public List<Charge> queryChargeList(Charge condition);

    public Charge getCharge(String code);
}
