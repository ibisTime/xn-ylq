package com.cdkj.ylq.dto.req;

import java.util.List;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午7:48:53 
 * @history:
 */
public class XN802300Req extends APageReq {

    private static final long serialVersionUID = 5582592396364166469L;

    // 账户编号
    private String accountNumber;

    // 用户编号
    private String userId;

    // 币种
    private String currency;

    // 类别（B端账号，C端账号，平台账号）
    private String type;

    // 状态（正常/程序冻结/人工冻结）
    private String status;

    // 币种列表
    private List<String> currencyList;

    // 最近一次变动对应的流水编号
    private String lastOrder;

    // 手机号模糊查
    private String mobileForQuery;

    // 真实姓名模糊查
    private String relaNameForQuery;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    public String getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(String lastOrder) {
        this.lastOrder = lastOrder;
    }

    public String getMobileForQuery() {
        return mobileForQuery;
    }

    public void setMobileForQuery(String mobileForQuery) {
        this.mobileForQuery = mobileForQuery;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRelaNameForQuery() {
        return relaNameForQuery;
    }

    public void setRelaNameForQuery(String relaNameForQuery) {
        this.relaNameForQuery = relaNameForQuery;
    }

}
