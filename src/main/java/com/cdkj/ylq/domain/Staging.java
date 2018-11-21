package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 分期记录
* @author: taojian 
* @since: 2018-11-21 20:32:36
* @history:
*/
public class Staging extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 申请人数
    private String applyUser;

    // 借款编号
    private String orderCode;

    // 支付金额
    private BigDecimal payAmount;

    // 最晚支付日期
    private Date lastPayDate;

    // 支付时间
    private Date payDatetime;

    // 支付类型
    private String payType;

    // 支付编号
    private String payCode;

    // 支付组号
    private String payGroup;

    // 状态
    private String status;

    // 第几期
    private Integer count;

    // 批次号
    private Integer batch;

    // 公司编号
    private String companyCode;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setLastPayDate(Date lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public Date getLastPayDate() {
        return lastPayDate;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

}
