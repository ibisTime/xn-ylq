package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 注册用户
 * @author: jiafr 
 * @since: 2018年9月28日 下午7:54:40 
 * @history:
 */
public class XN630060Req {

    // 类型
    @NotBlank
    private String kind;

    // 手机号
    @NotBlank
    private String mobile;

    // 登录密码
    @NotBlank
    private String loginPwd;

    // 验证码
    @NotBlank
    private String smsCaptcha;

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
