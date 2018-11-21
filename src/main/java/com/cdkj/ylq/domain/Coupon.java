package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 优惠券
* @author: haiqingzheng
* @since: 2017年08月16日 11:15:58
* @history:
*/
public class Coupon extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 获得条件
    private Integer condition;

    // 金额
    private BigDecimal amount;

    // 有效天数
    private Integer validDays;

    // 起用金额
    private Long startAmount;

    // 状态
    private String status;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Long getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(Long startAmount) {
        this.startAmount = startAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
