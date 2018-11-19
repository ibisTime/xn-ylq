package com.cdkj.ylq.dto.req;

/**
 * 绑定银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:30:58 
 * @history:
 */
public class XN802020Req {
    // 系统编号（必填）
    private String systemCode;

    // 银行编号
    private String bankCode;

    // 卡号（必填）
    private String bankcardNumber;

    // 银行名称（必填）
    private String bankName;

    // 支行名称（必填）
    private String subbranch;

    // 绑定手机号（必填）
    private String bindMobile;

    // 验证码（必填）
    private String smsCaptcha;

    // 用户编号（必填）
    private String userId;

    // 用户姓名（必填）
    private String realName;

    // 类型（必填）
    private String type;

    // 币种（必填）
    private String currency;

    // 备注（选填）
    private String remark;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
