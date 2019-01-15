/**
 * @Title XN623217Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月15日 上午11:35:33 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;


/** 
 * @author: taojian 
 * @since: 2019年1月15日 上午11:35:33 
 * @history:
 */
public class XN623217Req {

    private String bankCode;

    private String subbranch;

    private String ownerName;

    private String status;

    private String companyCode;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
