package com.cdkj.ylq.dto.res;

import java.math.BigDecimal;

/**
 * 提现金额统计
 * @author: silver 
 * @since: Oct 22, 2018 3:56:36 PM 
 * @history:
 */
public class XN629903Res {
    // 提现金额
    private BigDecimal totalAmount;

    public XN629903Res(BigDecimal totalAmount) {
        super();
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
