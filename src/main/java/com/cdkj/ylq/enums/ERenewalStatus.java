package com.cdkj.ylq.enums;

public enum ERenewalStatus {
    TO_PAY("0", "待支付"), PAY_YES("1", "已支付");

    ERenewalStatus(String code, String value) {
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
