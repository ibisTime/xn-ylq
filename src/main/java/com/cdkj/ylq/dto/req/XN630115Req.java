/**
 * @Title XN630115Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午9:32:25 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: taojian 
 * @since: 2018年11月20日 下午9:32:25 
 * @history:
 */
public class XN630115Req extends APageReq {

    private static final long serialVersionUID = 1232687217971645772L;

    private String roleCode;

    private String updater;

    private String keyword;

    private String status;

    private String companyCode;

    private String isJt;

    private String isFk;

    private String isDl;

    private String isAdmin;

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
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

    public String getKeyword() {
        return keyword;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
