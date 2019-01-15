/**
 * @Title XN623210Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月15日 上午11:11:40 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 新增收款账号
 * @author: taojian 
 * @since: 2019年1月15日 上午11:11:40 
 * @history:
 */
public class XN623210Req {

    // 图片
    private String pict;

    // 银行卡号
    @NotBlank
    private String bankcardNumber;

    // 银行行别
    @NotBlank
    private String bankCode;

    // 银行名称
    @NotBlank
    private String bankName;

    // 开户支行
    private String subbranch;

    // 卡主姓名
    @NotBlank
    private String ownerName;

    // 备注
    private String remark;

    // 公司编号
    @NotBlank
    private String companyCode;

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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
