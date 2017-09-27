package com.cdkj.ylq.enums;

public enum EApplyType {
    JZB("1", "九州宝"), JDT("2", "借贷通");

    EApplyType(String code, String value) {
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
