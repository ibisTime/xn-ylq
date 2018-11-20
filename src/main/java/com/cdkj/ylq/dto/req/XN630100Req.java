/**
 * @Title XN630100Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午7:55:02 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月20日 下午7:55:02 
 * @history:
 */
public class XN630100Req {

    // 角色编号
    @NotBlank
    private String roleCode;

    // 真名
    @NotBlank
    private String realName;

    // 头像
    private String photo;

    // 手机号
    @NotBlank
    private String mobile;

    // 登录名
    @NotBlank
    private String loginName;

    // 登陆密码
    @NotBlank
    private String loginPwd;

    // 是否借条
    @NotBlank
    private String isJt;

    // 是否风控
    @NotBlank
    private String isFk;

    // 是否导流
    @NotBlank
    private String isDl;

    // 备注
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
