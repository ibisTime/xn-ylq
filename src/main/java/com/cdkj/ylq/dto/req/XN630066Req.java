package com.cdkj.ylq.dto.req;

/**
 * 分页查询用户
 * @author: chenshan 
 * @since: 2018年3月26日 下午6:28:36 
 * @history:
 */
public class XN630066Req extends AListReq {

    private static final long serialVersionUID = 2531978925998093639L;

    // （选填）用户名
    private String realName;

    // （选填）角色
    private String roleCode;

    public String getRealName() {
        return realName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}
