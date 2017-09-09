package com.cdkj.ylq.dto.req;

public class XN805041Req {
    // 手机号(必填)
    private String mobile;

    // 注册密码(必填)
    private String loginPwd;

    // 推荐人类型(选填)
    private String userRefereeKind;

    // 推荐人(选填)
    private String userReferee;

    // 手机验证码(必填)
    private String smsCaptcha;

    // 类别(必填)
    private String kind;

    // 省(选填)
    private String province;

    // 市(选填)
    private String city;

    // 区(选填)
    private String area;

    // 详细地址(选填)
    private String address;

    // 是否注册环信(必填)
    private String isRegHx;

    // 公司编号(必填)
    private String companyCode;

    // 系统编号(必填)
    private String systemCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUserRefereeKind() {
        return userRefereeKind;
    }

    public void setUserRefereeKind(String userRefereeKind) {
        this.userRefereeKind = userRefereeKind;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsRegHx() {
        return isRegHx;
    }

    public void setIsRegHx(String isRegHx) {
        this.isRegHx = isRegHx;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
