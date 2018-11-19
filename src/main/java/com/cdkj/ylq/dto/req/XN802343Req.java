package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 线下充值申请
 * @author: xieyj 
 * @since: 2017年5月12日 上午9:58:02 
 * @history:
 */
public class XN802343Req {

    @NotBlank
    private String userId;

    @NotBlank
    private String payType;

    @NotBlank
    private String amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
