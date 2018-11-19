package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增银行
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN802110Req {

    // 银行别称
    @NotBlank(message = "银行代号不能为空")
    private String bankCode;

    // 银行名称
    @NotBlank(message = "银行名称不能为空")
    private String bankName;

    // 渠道类型，35：微信公众号支付, 90：线下转账 ，0：内部帐，1：微信小程序支付，2：银行卡
    @NotBlank(message = "渠道类型不能为空")
    private String channelType;

    // 渠道给银行的代号
    @NotBlank(message = "渠道银行代号不能为空")
    private String channelBank;

    // 每日限额
    @NotBlank(message = "每日限额不能为空")
    private String dayAmount;

    // 每日笔数限制
    @NotBlank(message = "每日笔数限制不能为空")
    private String maxOrder;

    // 每月限额
    @NotBlank(message = "每月限额不能为空")
    private String monthAmount;

    // 单笔限额
    @NotBlank(message = "单笔限额不能为空")
    private String orderAmount;

    // 状态，0 不可用 1可用
    @NotBlank(message = "状态不能为空")
    private String status;

    // 备注
    private String remark;

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelbank() {
        return channelBank;
    }

    public void setChannelbank(String channelBank) {
        this.channelBank = channelBank;
    }

    public String getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(String dayAmount) {
        this.dayAmount = dayAmount;
    }

    public String getMaxOrder() {
        return maxOrder;
    }

    public void setMaxOrder(String maxOrder) {
        this.maxOrder = maxOrder;
    }

    public String getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(String monthAmount) {
        this.monthAmount = monthAmount;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
