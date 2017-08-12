package com.cdkj.ylq.enums;

public enum EApplyStatus {
    TO_CERTI("1", "认证中"), TO_APPROVE("2", "人工审核中"), APPROVE_NO("3", "已驳回"), APPROVE_YES(
            "4", "已有额度"), TO_LOAN("5", "等待放款中"), LOANING("6", "生效中"), OVERDUE(
            "7", "已逾期"), REPAY("8", "已还款"), CANCEL("9", "已取消");

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
