package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
 * 系统用户
 * @author: jiafr 
 * @since: 2018年9月26日 下午5:35:21 
 * @history:
 */
public class SYSUser extends ABaseDO {

    private static final long serialVersionUID = -332310384678966884L;

    // ***********db properties***********

    // 用户id
    private String userId;

    // 类型（1=平台）
    private String kind;

    // 角色编号
    private String roleCode;

    // 真实姓名
    private String realName;

    // 头像
    private String photo;

    // 电话
    private String mobile;

    // 登陆名
    private String loginName;

    // 登陆密码
    private String loginPwd;

    // 登录密码强度
    private String loginPwdStrength;

    // 创建时间
    private Date createDatetime;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ***********db properties***********

    // 注册时间起
    private Date createDatetimeStart;

    // 注册时间止
    private Date createDatetimeEnd;

    // 手机模糊查询
    private String mobileForQuery;

    // 名字模糊查询
    private String realNameForQuery;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getMobileForQuery() {
        return mobileForQuery;
    }

    public void setMobileForQuery(String mobileForQuery) {
        this.mobileForQuery = mobileForQuery;
    }

    public String getRealNameForQuery() {
        return realNameForQuery;
    }

    public void setRealNameForQuery(String realNameForQuery) {
        this.realNameForQuery = realNameForQuery;
    }

}
