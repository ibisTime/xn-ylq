package com.cdkj.ylq.enums;

public enum ERepayApplyType {
    REPAY("0", "还款"), STAGE("1", "分期还款");

    ERepayApplyType(String code, String value) {
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
