package com.cdkj.ylq.dto.req;

public class XN805050Req {
    // 登陆名（必填）
    private String loginName;

    // 登陆密码（必填）
    private String loginPwd;

    // 用户类型（选填）
    private String kind;

    // 公司编号（选填）
    private String companyCode;

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
