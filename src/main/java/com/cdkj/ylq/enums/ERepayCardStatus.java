package com.cdkj.ylq.enums;

public enum ERepayCardStatus {
    TOOPEN("0", "待开启"), OPEN("1", "开启"), CLOSE("2", "关闭");

    ERepayCardStatus(String code, String value) {
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
