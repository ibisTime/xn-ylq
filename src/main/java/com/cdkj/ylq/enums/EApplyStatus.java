package com.cdkj.ylq.enums;

public enum EApplyStatus {
    TO_CERTI("1", "认证中"), TO_APPROVE("2", "待审核"), APPROVE_NO("3", "已驳回"), APPROVE_YES(
            "4", "审核通过"), CANCEL("5", "取消");

    EApplyStatus(String code, String value) {
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
