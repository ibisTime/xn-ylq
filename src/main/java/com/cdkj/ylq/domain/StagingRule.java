package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 分期规则
* @author: taojian 
* @since: 2018-11-21 10:39:30
* @history:
*/
public class StagingRule extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 期数
    private Long count;

    // 每期周期
    private Long cycle;

    // 日利率
    private BigDecimal rate;

    // 次序
    private Long orderNo;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 公司编号
    private String companyCode;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCycle(Long cycle) {
        this.cycle = cycle;
    }

    public Long getCycle() {
        return cycle;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

}
