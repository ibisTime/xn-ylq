package com.cdkj.ylq.enums;

public enum ERepayApplyStatus {
    TO_APPROVE("0", "待审核"), APPROVE_YES("1", "审核通过"), APPROVE_NO("2", "审核不通过");

    ERepayApplyStatus(String code, String value) {
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
