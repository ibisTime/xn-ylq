package com.cdkj.ylq.enums;

public enum ECertificationStatus {
    TO_CERTI("0", "未认证"), CERTI_YES("1", "已认证"), INVALID("2", "已过期"), CERTING(
            "3", "认证中"), FAILED("4", "认证失败");

    ECertificationStatus(String code, String value) {
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
