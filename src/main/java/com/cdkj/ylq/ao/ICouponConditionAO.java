package com.cdkj.ylq.ao;

public interface ICouponConditionAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    // 推荐成功
    public void recommendSuccess(String userId);

    // 还款成功
    public void repaySuccess(String userId);

}
