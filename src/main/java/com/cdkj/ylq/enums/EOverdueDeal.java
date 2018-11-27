package com.cdkj.ylq.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EOverdueDeal {
    REPAY("0", "逾期后还款"), STAGE("1", "逾期后分期"), BAD("2", "逾期后确认坏账");

    EOverdueDeal(String code, String value) {
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
