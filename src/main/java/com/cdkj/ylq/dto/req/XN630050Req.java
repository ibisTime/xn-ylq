package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增用户
 * @author: taojian 
 * @since: 2018年9月10日 上午10:07:50 
 * @history:
 */
public class XN630050Req {

    // 角色编号
    @NotBlank
    private String roleCode;

    // （必填）真实姓名
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    // （必填）手机号
    @NotBlank(message = "不能为空")
    private String mobile;

    // （必填）登陆名
    @NotBlank(message = "不能为空")
    private String loginName;

    // （必填）登录密码
    @NotBlank(message = "不能为空")
    private String loginPwd;

    // （选填）头像
    private String photo;

    // （选填）备注
    private String remark;

    // 是否借条模块
    @NotBlank
    private String isJt;

    // 是否风控
    @NotBlank
    private String isFk;

    // 是否导流
    @NotBlank
    private String isDl;

    public String getIsJt() {
        return isJt;
    }

    public void setIsJt(String isJt) {
        this.isJt = isJt;
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

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
