package com.cdkj.ylq.dto.req;

/**
 * 列表查询各端账户总余额
 * @author: silver 
 * @since: Oct 9, 2018 8:44:54 PM 
 * @history:
 */
public class XN802302Req {
    // 币种
    private String currency;

    // 状态
    private String status;

    // 类型
    private String type;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
