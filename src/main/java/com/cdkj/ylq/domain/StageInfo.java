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

    // 金额
    private BigDecimal amount;

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
}
