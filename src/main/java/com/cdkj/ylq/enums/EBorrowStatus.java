package com.cdkj.ylq.enums;

public enum EBorrowStatus {
    TO_APPROVE("0", "待审核"), APPROVE_YES("1", "审核通过待放款"), APPROVE_NO("2",
            "审核不通过"), LOANING("3", "已放款"), LOAN_NO("7", "打款失败"), REPAY("4",
            "已还款"), OVERDUE("5", "已逾期"), BAD("6", "确认坏账"), PAY_SUBMIT("8",
            "代付转账中");

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
