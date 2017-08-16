package com.cdkj.ylq.enums;

public enum ECouponType {
    RECOMMENT("0", "获客优惠券"), BORROW("1", "借还优惠券");

    ECouponType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
