package com.cdkj.ylq.dto.res;

public class XN630101Res {

    // 编号
    private String userId;

    private String companyCode;

    public XN630101Res() {
    }

    public XN630101Res(String userId, String companyCode) {
        this.userId = userId;
        this.companyCode = companyCode;
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
