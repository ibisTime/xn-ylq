/**
 * @Title XN623100Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 上午11:33:10 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月16日 上午11:33:10 
 * @history:
 */
public class XN623100Req {

    // 编号（必填）
    private String code;

    // 获得条件（必填）
    private String condition;

    // 金额（必填）
    private String amount;

    // 有效天数（必填）
    private String validDays;

    // 起用金额（必填）
    private String startAmount;

    // 最后更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getValidDays() {
        return validDays;
    }

    public void setValidDays(String validDays) {
        this.validDays = validDays;
    }

    public String getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
