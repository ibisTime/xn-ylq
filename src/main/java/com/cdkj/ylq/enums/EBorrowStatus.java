package com.cdkj.ylq.enums;

public enum EBorrowStatus {
    TO_LOAN("0", "等待放款中"), LOANING("1", "生效中"), OVERDUE("2", "已逾期"), REPAY("3",
            "已还款"), CANCEL("4", "已取消"), BAD("5", "确认坏账");

    EBorrowStatus(String code, String value) {
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
