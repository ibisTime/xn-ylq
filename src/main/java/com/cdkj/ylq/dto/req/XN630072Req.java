package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 重置支付密码
 * @author: silver 
 * @since: Oct 6, 2018 11:32:52 AM 
 * @history:
 */
public class XN630072Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 交易秘密
    @NotBlank
    private String newTradePwd;

    // 验证码
    @NotBlank
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
