package com.cdkj.ylq.dto.req;

/**
 * 菜单角色-查询菜单列表Req
 * @author: xieyj 
 * @since: 2016年5月16日 下午10:45:20 
 * @history:
 */
public class XN805026Req {

    // 角色编号（必填）
    private String roleCode;

    // 类型(选填)
    private String type;

    // 父菜单编号(选填)
    private String parentCode;

    // 系统编号(必填)
    private String companyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
