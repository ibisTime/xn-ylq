package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.ylq.dao.base.ABaseDO;

public class Account extends ABaseDO {
    private static final long serialVersionUID = -6598493388816020642L;

    // 账户编号
    private String accountNumber;

    // 用户编号
    private String userId;

    // 币种（CNY人民币TPP碳泡泡JF积分）
    private String currency;

    // 类别（C端账号O产权方账号M养护方账号A代理商账号P平台账号）
    private String type;

    // 状态（0正常/1程序冻结/2人工冻结）
    private String status;

    // 余额
    private BigDecimal amount;

    private BigDecimal totalAmount;

    // 冻结金额
    private BigDecimal frozenAmount;

    // md5
    private String md5;

    // 总充值金额（入金）
    private BigDecimal inAmount;

    // 总取现金额（出金）
    private BigDecimal outAmount;

    // 创建时间
    private Date createDatetime;

    // 最近一次变动对应的流水编号
    private String lastOrder;

    // **************************db properties **************************

    // 创建起始时间
    private Date createDatetimeStart;

    // 创建终止时间
    private Date createDatetimeEnd;

    // 币种列表
    private List<String> currencyList;

    // 真实姓名
    private String realName;

    // 手机号
    private String mobile;

    // 手机号模糊查
    private String mobileForQuery;

    // 历史总发放额(积分/碳泡泡)
    private BigDecimal historyOutAmount;

    // 历史总回收额(积分/碳泡泡)
    private BigDecimal historyInAmount;

    // 真实姓名模糊查
    private String relaNameForQuery;

    // 系统编号
    private String systemCode;

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(String lastOrder) {
        this.lastOrder = lastOrder;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public BigDecimal getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileForQuery() {
        return mobileForQuery;
    }

    public void setMobileForQuery(String mobileForQuery) {
        this.mobileForQuery = mobileForQuery;
    }

    public BigDecimal getHistoryOutAmount() {
        return historyOutAmount;
    }

    public void setHistoryOutAmount(BigDecimal historyOutAmount) {
        this.historyOutAmount = historyOutAmount;
    }

    public BigDecimal getHistoryInAmount() {
        return historyInAmount;
    }

    public void setHistoryInAmount(BigDecimal historyInAmount) {
        this.historyInAmount = historyInAmount;
    }

    public String getRelaNameForQuery() {
        return relaNameForQuery;
    }

    public void setRelaNameForQuery(String relaNameForQuery) {
        this.relaNameForQuery = relaNameForQuery;
    }
}
