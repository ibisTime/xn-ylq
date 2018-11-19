package com.cdkj.ylq.dto.req;

import java.util.List;

public class XN802320Req extends APageReq {

    private static final long serialVersionUID = -505923347145288692L;

    // 类型
    private String type;

    // 用户编号
    private String userId;

    // 流水所属账号---核心字段4
    private String accountNumber;

    // 类型(B B端账号，C C端账号，P 平台账号)
    private String accountType;

    // 币种
    private String currency;

    // 业务类型
    private String bizType;

    // 状态
    private String status;

    // 支付渠道（线下/招商代付/支付宝/内部转账）
    private String channelType;

    // 支付渠道单号（支付渠道代表）
    private String channelOrder;

    // 流水分组组号（橙账本代表）
    private String refNo;

    // 币种列表
    private List<String> currencyList;

    // 开始时间起
    private String createDatetimeStart;

    // 开始时间止
    private String createDatetimeEnd;

    // 拟对账时间
    private String workDate;

    // 对账人
    private String checkUser;

    // 调账人
    private String adjustUser;

    // 户名模糊查
    private String relaNameForQuery;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    public String getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(String createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public String getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(String createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getAdjustUser() {
        return adjustUser;
    }

    public void setAdjustUser(String adjustUser) {
        this.adjustUser = adjustUser;
    }

    public String getRelaNameForQuery() {
        return relaNameForQuery;
    }

    public void setRelaNameForQuery(String relaNameForQuery) {
        this.relaNameForQuery = relaNameForQuery;
    }

}
