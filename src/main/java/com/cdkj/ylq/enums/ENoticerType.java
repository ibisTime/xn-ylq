package com.cdkj.ylq.enums;

public enum ENoticerType {
    Approver("0", "借款审批通知人"), Loaner("1", "放款通知人"), Credit("2", "信用分审批通知人");

    ENoticerType(String code, String value) {
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
