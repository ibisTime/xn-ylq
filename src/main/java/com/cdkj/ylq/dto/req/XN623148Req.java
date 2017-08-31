package com.cdkj.ylq.dto.req;

public class XN623148Req extends APageReq {

    private static final long serialVersionUID = 8379064020980760674L;

    // 用户编号（必填）
    private String userId;

    // 实际借款金额（必填）
    private String amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
