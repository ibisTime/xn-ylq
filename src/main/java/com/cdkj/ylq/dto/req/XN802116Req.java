package com.cdkj.ylq.dto.req;

public class XN802116Req extends AListReq {

    private static final long serialVersionUID = 6344984390159547359L;

    // 银行别称 （选填）
    private String bankCode;

    // 银行名称 （选填）
    private String bankName;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
