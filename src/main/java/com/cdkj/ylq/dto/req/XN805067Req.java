package com.cdkj.ylq.dto.req;

public class XN805067Req {

    // userId（必填）
    private String userId;

    // 新支付密码（必填）
    private String newTradePwd;

    // 短信验证码（必填）
    private String smsCaptcha;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewTradePwd() {
        return newTradePwd;
    }

    public void setNewTradePwd(String newTradePwd) {
        this.newTradePwd = newTradePwd;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
