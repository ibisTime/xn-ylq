package com.cdkj.ylq.dto.res;

public class XN630101Res {

    // 编号
    private String userId;

    private String companyCode;

    private String rootMenuCode;

    public String getRootMenuCode() {
        return rootMenuCode;
    }

    public void setRootMenuCode(String rootMenuCode) {
        this.rootMenuCode = rootMenuCode;
    }

    public XN630101Res() {
    }

    public XN630101Res(String userId, String companyCode, String rootMenuCode) {
        this.userId = userId;
        this.companyCode = companyCode;
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
