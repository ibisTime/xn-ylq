package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630051Req {
    // 登陆名（必填）
    @NotBlank
    private String loginName;

    // 登陆密码（必填）
    @NotBlank
    private String loginPwd;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

}
