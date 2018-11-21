package com.cdkj.ylq.domain;

import java.math.BigDecimal;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 逾期记录
* @author: haiqingzheng
* @since: 2017年09月07日 17:56:57
* @history:
*/
public class Overdue extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private Long id;

    // 用户编号
    private String userId;

    // 关联借款订单号
    private String borrowCode;

    // 逾期天数
    private Integer days;

    // 逾期金额
    private BigDecimal amount;

    // 逾期后处理
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
