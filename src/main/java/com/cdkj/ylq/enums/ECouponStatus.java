package com.cdkj.ylq.enums;

public enum ECouponStatus {
    OPEN("0", "关闭"), CLOSE("1", "启用");

    ECouponStatus(String code, String value) {
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
