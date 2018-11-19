package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 设置角色
 * @author: nyc 
 * @since: 2018年4月26日 下午5:54:31 
 * @history:
 */
public class XN630057Req {

    // 用户编号(必填)
    @NotBlank
    private String userId;

    // 角色编号(必填)
    @NotBlank
    private String roleCode;

    // 更新人(必填)
    @NotBlank
    private String updater;

    // 备注(选填)
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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
}
