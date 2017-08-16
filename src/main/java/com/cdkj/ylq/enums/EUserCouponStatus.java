package com.cdkj.ylq.enums;

public enum EUserCouponStatus {
    TO_USE("0", "可使用"), USED("1", "已使用"), INVALID("2", "已过期"), RECYCLE("3",
            "已回收");

    EUserCouponStatus(String code, String value) {
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
