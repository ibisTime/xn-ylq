package com.cdkj.ylq.dto.req;

import java.util.List;

/**
 * 取现分页查询
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:03:24 
 * @history:
 */
public class XN802355Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -2015437210978527476L;

    // 订单编号模糊查询
    private String code;

    // 账户编号
    private String accountNumber;

    // 账户类型
    private String accountType;

    // 币种
    private String currency;

    // 币种列表
    private List<String> currencyList;

    // 支付渠道
    private String channelType;

    // 支付渠道的订单编号（支付渠道代表）
    private String channelOrder;

    // 状态（待审核/审核不通过/审核通过待支付/支付成功/支付失败）
    private String status;

    // 申请人
    private String applyUser;

    // 审核人
    private String approveUser;

    // 支付回录人
    private String payUser;

    // ******** db *****

    // 申请时间起
    private String applyDateStart;

    // 申请时间止
    private String applyDateEnd;

    // 审批时间起
    private String approveDateStart;

    // 审批时间止
    private String approveDateEnd;

    // 支付时间起
    private String payDateStart;

    // 支付时间止
    private String payDateEnd;

    // 户名模糊查
    private String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelOrder() {
        return channelOrder;
    }

    public void setChannelOrder(String channelOrder) {
        this.channelOrder = channelOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(String applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public String getApplyDateEnd() {
        return applyDateEnd;
    }

    public void setApplyDateEnd(String applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }

    public String getApproveDateStart() {
        return approveDateStart;
    }

    public void setApproveDateStart(String approveDateStart) {
        this.approveDateStart = approveDateStart;
    }

    public String getApproveDateEnd() {
        return approveDateEnd;
    }

    public void setApproveDateEnd(String approveDateEnd) {
        this.approveDateEnd = approveDateEnd;
    }

    public String getPayDateStart() {
        return payDateStart;
    }

    public void setPayDateStart(String payDateStart) {
        this.payDateStart = payDateStart;
    }

    public String getPayDateEnd() {
        return payDateEnd;
    }

    public void setPayDateEnd(String payDateEnd) {
        this.payDateEnd = payDateEnd;
    }

}
