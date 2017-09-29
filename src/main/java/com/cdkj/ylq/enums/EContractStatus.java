package com.cdkj.ylq.enums;

public enum EContractStatus {
    ING("0", "执行中"), ZCHK("1", "正常还款"), YQHK("2", "逾期还款"), END("3", "平台终止"), HZ(
            "7", "坏账");

    EContractStatus(String code, String value) {
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
