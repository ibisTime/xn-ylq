package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN802301Req {

    // 账户编号(必填)
    @NotBlank
    private String userId;

    // 币种(选填)
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
