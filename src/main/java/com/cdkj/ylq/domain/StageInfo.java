/**
 * @Title StageInfo.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月26日 下午8:15:50 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;

/** 
 * @author: taojian 
 * @since: 2018年11月26日 下午8:15:50 
 * @history:
 */
public class StageInfo {

    // 分期编号
    private String stageCode;

    // 日期
    private String date;

    // 本期开始时间
    private Date startTime;

    // 本期结束时间
    private Date endTime;

    // 利息
    private BigDecimal lxAmount;

    // 本金
    private BigDecimal mainAmount;

    // 总金额金额
    private BigDecimal amount;

    // 状态
    private String status;

    // 第几期
    private Integer stageCount;

    // 备注
    private String remark;

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getLxAmount() {
        return lxAmount;
    }

    public void setLxAmount(BigDecimal lxAmount) {
        this.lxAmount = lxAmount;
    }

    public BigDecimal getMainAmount() {
        return mainAmount;
    }

    public void setMainAmount(BigDecimal mainAmount) {
        this.mainAmount = mainAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStageCount() {
        return stageCount;
    }

    public void setStageCount(Integer stageCount) {
        this.stageCount = stageCount;
    }
}
