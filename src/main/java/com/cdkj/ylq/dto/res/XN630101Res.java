package com.cdkj.ylq.dto.res;

public class XN630101Res {

    // 编号
    private String userId;

    private String companyCode;

    private String rootMenuCode;

    private String isJt;

    private String isFk;

    private String isDl;

    private String appName;

    private String appLogo;

    public XN630101Res() {
    }

    public XN630101Res(String userId, String companyCode, String rootMenuCode,
            String isJt, String isFk, String isDl, String appName,
            String appLogo) {
        this.userId = userId;
        this.companyCode = companyCode;
        this.rootMenuCode = rootMenuCode;
        this.isDl = isDl;
        this.isFk = isFk;
        this.isJt = isJt;
        this.appName = appName;
        this.appLogo = appLogo;
    }

    public String getIsJt() {
        return isJt;
    }

    public void setIsJt(String isJt) {
        this.isJt = isJt;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getIsFk() {
        return isFk;
    }

    public void setIsFk(String isFk) {
        this.isFk = isFk;
    }

    public String getIsDl() {
        return isDl;
    }

    public void setIsDl(String isDl) {
        this.isDl = isDl;
    }

    public String getRootMenuCode() {
        return rootMenuCode;
    }

    public void setRootMenuCode(String rootMenuCode) {
        this.rootMenuCode = rootMenuCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
