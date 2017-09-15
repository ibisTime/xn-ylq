/**
 * @Title BaofooPay.java 
 * @Package com.std.account.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月13日 下午4:44:55 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月13日 下午4:44:55 
 * @history:
 */
public class BaofooPay {

    private String transNo;// 商户订单号

    private Long transMoney;// 转账金额

    private String toAccName;// 收款人姓名

    private String toAccNo;// 收款人银行帐号

    private String toBankName;// 收款人银行名称

    private String toProName;// 收款人开户行省名

    private String toCityName;// 收款人开户行市名

    private String toAccDept;// 收款人开户行机构名

    private String transCardId;// 身份证号码

    private String transMobile;// 手机号

    private String transSummary;// 摘要

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public Long getTransMoney() {
        return transMoney;
    }

    public void setTransMoney(Long transMoney) {
        this.transMoney = transMoney;
    }

    public String getToAccName() {
        return toAccName;
    }

    public void setToAccName(String toAccName) {
        this.toAccName = toAccName;
    }

    public String getToAccNo() {
        return toAccNo;
    }

    public void setToAccNo(String toAccNo) {
        this.toAccNo = toAccNo;
    }

    public String getToBankName() {
        return toBankName;
    }

    public void setToBankName(String toBankName) {
        this.toBankName = toBankName;
    }

    public String getToProName() {
        return toProName;
    }

    public void setToProName(String toProName) {
        this.toProName = toProName;
    }

    public String getToCityName() {
        return toCityName;
    }

    public void setToCityName(String toCityName) {
        this.toCityName = toCityName;
    }

    public String getToAccDept() {
        return toAccDept;
    }

    public void setToAccDept(String toAccDept) {
        this.toAccDept = toAccDept;
    }

    public String getTransCardId() {
        return transCardId;
    }

    public void setTransCardId(String transCardId) {
        this.transCardId = transCardId;
    }

    public String getTransMobile() {
        return transMobile;
    }

    public void setTransMobile(String transMobile) {
        this.transMobile = transMobile;
    }

    public String getTransSummary() {
        return transSummary;
    }

    public void setTransSummary(String transSummary) {
        this.transSummary = transSummary;
    }

}
