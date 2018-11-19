package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改支付密码
 * @author: silver 
 * @since: Oct 6, 2018 11:32:52 AM 
 * @history:
 */
public class XN630071Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 新支付密码
    @NotBlank
    private String newTradePwd;

    // 旧支付密码
    @NotBlank
    private String oldTradePwd;

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

    public String getOldTradePwd() {
        return oldTradePwd;
    }

    public void setOldTradePwd(String oldTradePwd) {
        this.oldTradePwd = oldTradePwd;
    }

}
