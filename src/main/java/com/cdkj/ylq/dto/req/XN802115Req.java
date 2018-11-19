package com.cdkj.ylq.dto.req;

public class XN802115Req extends APageReq {

    private static final long serialVersionUID = -4578161783507127781L;

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
