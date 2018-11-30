/**
 * @Title XN623000Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月11日 下午2:47:20 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月11日 下午2:47:20 
 * @history:
 */
public class XN623001Req {

    // 编号（必填）
    private String code;

    // 名称（必填）
    private String name;

    // 广告语（选填）
    private String slogan;

    // 等级（必填）
    private String level;

    // 借款金额（必填）
    private String amount;

    // 借款时长（必填）
    private String duration;

    // 7天内逾期利率（必填）
    private String yqRate1;

    // 7天外逾期利率（必填）
    private String yqRate2;

    // 利息（必填）
    private String lxRate;

    // 快速信审费（必填）
    private String xsAmount;

    // 账户管理费（必填）
    private String glAmount;

    // 服务费（必填）
    private String fwAmount;

    // 最后更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getYqRate1() {
        return yqRate1;
    }

    public void setYqRate1(String yqRate1) {
        this.yqRate1 = yqRate1;
    }

    public String getYqRate2() {
        return yqRate2;
    }

    public void setYqRate2(String yqRate2) {
        this.yqRate2 = yqRate2;
    }

    public String getLxRate() {
        return lxRate;
    }

    public void setLxRate(String lxRate) {
        this.lxRate = lxRate;
    }

    public String getXsAmount() {
        return xsAmount;
    }

    public void setXsAmount(String xsAmount) {
        this.xsAmount = xsAmount;
    }

    public String getGlAmount() {
        return glAmount;
    }

    public void setGlAmount(String glAmount) {
        this.glAmount = glAmount;
    }

    public String getFwAmount() {
        return fwAmount;
    }

    public void setFwAmount(String fwAmount) {
        this.fwAmount = fwAmount;
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
