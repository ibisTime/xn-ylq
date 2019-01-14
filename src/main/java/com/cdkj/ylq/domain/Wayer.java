package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 渠道商
* @author: taojian 
* @since: 2019-01-11 17:24:08
* @history:
*/
public class Wayer extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 用户编号
    private String userId;

    // 公司编号
    private String companyCode;

    // 链接名字
    private String name;

    // 登陆名
    private String loginName;

    // 登陆密码
    private String loginPwd;

    // 等密码强度
    private String loginPwdStrength;

    // 创建时间
    private Date createDatetime;

    // 注册用户总数
    private Long userCount;

    // 链接数
    private Long urlCount;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUrlCount(Long urlCount) {
        this.urlCount = urlCount;
    }

    public Long getUrlCount() {
        return urlCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}
