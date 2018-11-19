package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 线下取现申请
 * @author: xieyj 
 * @since: 2016年12月25日 下午3:25:44 
 * @history:
 */
public class XN802350Req {

    // 针对账号（必填）
    @NotBlank
    private String accountNumber;

    // 取现金额（必填）
    @NotBlank
    private String amount;

    // 支付渠道账号信息（如开户支行）（必填）
    @NotBlank
    private String payCardInfo;

    // 支付渠道账号（如银行卡号）（必填）
    @NotBlank
    private String payCardNo;

    // 资金密码
    @NotBlank
    private String tradePwd;

    // 申请人（必填）
    @NotBlank
    private String applyUser;

    // 申请人类型（必填）
    @NotBlank
    private String applyUserType;

    // 申请说明（选填）
    private String applyNote;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayCardInfo() {
        return payCardInfo;
    }

    public void setPayCardInfo(String payCardInfo) {
        this.payCardInfo = payCardInfo;
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getApplyUserType() {
        return applyUserType;
    }

    public void setApplyUserType(String applyUserType) {
        this.applyUserType = applyUserType;
    }

}
