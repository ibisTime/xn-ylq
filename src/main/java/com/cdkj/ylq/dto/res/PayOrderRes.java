package com.cdkj.ylq.dto.res;

public class PayOrderRes {

    // 编号
    private String signOrder;

    public PayOrderRes() {
    }

    public PayOrderRes(String signOrder) {
        this.signOrder = signOrder;
    }

    public String getSignOrder() {
        return signOrder;
    }

    public void setSignOrder(String signOrder) {
        this.signOrder = signOrder;
    }
}
