package com.cdkj.ylq.dto.req;

public class XN630065Req extends APageReq {

    private static final long serialVersionUID = -7049202319583663210L;

    // （选填）用户名
    private String realName;

    // （选填）角色
    private String roleCode;

    // // （选填）开始日期
    // private String dateStart;
    //
    // // （选填）截止日期
    // private String dateEnd;

    // （选填）部门编号
    private String companyCode;

    // （选填）关键字
    private String keyword;

    // （选填）状态
    private String status;

    // （选填）操作人
    private String updater;

    // （选填）类型
    private String kind;

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

    // public String getDateStart() {
    // return dateStart;
    // }
    //
    // public void setDateStart(String dateStart) {
    // this.dateStart = dateStart;
    // }
    //
    // public String getDateEnd() {
    // return dateEnd;
    // }
    //
    // public void setDateEnd(String dateEnd) {
    // this.dateEnd = dateEnd;
    // }

    public String getKeyword() {
        return keyword;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
