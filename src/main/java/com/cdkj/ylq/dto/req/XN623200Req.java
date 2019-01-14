/**
 * @Title XN623200Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月14日 上午10:57:07 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 新增渠道商
 * @author: taojian 
 * @since: 2019年1月14日 上午10:57:07 
 * @history:
 */
public class XN623200Req {

    // 渠道名字
    @NotBlank
    private String name;

    // 登录名
    @NotBlank
    private String loginName;

    // 登陆密码
    @NotBlank
    private String loginPwd;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    // 公司编号
    @NotBlank
    private String companyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
