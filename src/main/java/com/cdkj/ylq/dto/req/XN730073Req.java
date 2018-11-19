package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 提交资料
 * @author: xieyj 
 * @since: 2018年10月4日 上午12:58:16 
 * @history:
 */
public class XN730073Req {

    private static final long serialVersionUID = -6308551102694787370L;

    // 用户编号
    @NotBlank
    private String userId;

    // 推荐代理手机号
    private String refUserMobile;

    // 公司名称
    private String name;

    // 负责人
    @NotBlank
    private String charger;

    // 联系方式
    @NotBlank
    private String chargeMobile;

    // 省
    @NotBlank
    private String province;

    // 市
    @NotBlank
    private String city;

    // 区
    @NotBlank
    private String area;

    // 地址
    @NotBlank
    private String address;

    // 简介
    @NotBlank
    private String description;

    // 营业执照
    private String bussinessLicense;

    // 组织机构代码
    private String organizationCode;

    // 更新人
    @NotBlank
    private String updater;

    public String getRefUserMobile() {
        return refUserMobile;
    }

    public void setRefUserMobile(String refUserMobile) {
        this.refUserMobile = refUserMobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getChargeMobile() {
        return chargeMobile;
    }

    public void setChargeMobile(String chargeMobile) {
        this.chargeMobile = chargeMobile;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBussinessLicense() {
        return bussinessLicense;
    }

    public void setBussinessLicense(String bussinessLicense) {
        this.bussinessLicense = bussinessLicense;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
