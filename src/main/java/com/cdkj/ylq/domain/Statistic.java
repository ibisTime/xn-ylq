package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 统计数据
* @author: haiqingzheng
* @since: 2017年12月26日 20:00:38
* @history:
*/
public class Statistic extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID主键
    private Long id;

    // 日期
    private Date today;

    // 注册人数
    private Long regNum;

    // 认证人数
    private Long certiNum;

    // 芝麻认证人数
    private Long zmxyNum;

    // 运营商认证人数
    private Long carrierNum;

    // 借贷通导流人数
    private Long jdtNum;

    // 申请人数
    private Long applyNum;

    // 放款单量
    private Long fkNum;

    // 放款金额
    private Long fkAmount;

    // 订单金额
    private Long orderAmount;

    // 当日应还款单量
    private Long yhkNum;

    // 当日应还款金额
    private Long yhkAmount;

    // 实际还款总单量
    private Long sjhkNum;

    // 宝付代扣还款单量
    private Long bfhkNum;

    // 宝付代扣还款金额
    private Long bfhkAmount;

    // 支付宝还款单量
    private Long zfbhkNum;

    // 支付宝还款金额
    private Long zfbhkAmount;

    // 实际还款总金额
    private Long hkAmount;

    // 续期总单量
    private Long xqNum;

    // 宝付代扣续期单量
    private Long bfdkNum;

    // 宝付代扣续期金额
    private Long bfdkAmount;

    // 支付宝代扣续期单量
    private Long zfbdkNum;

    // 支付宝代扣续期金额
    private Long zfbdkAmount;

    // 续期总金额
    private Long xqTotalAmount;

    // 逾期还款单量
    private Long yqhkNum;

    // 逾期还款金额
    private Long yqhkAmount;

    // 宝付代扣逾期还款单量
    private Long bfyqNum;

    // 宝付代扣逾期还款金额
    private Long bfyqTotalAmount;

    // 支付宝逾期还款单量
    private Long zfbyqNum;

    // 支付宝逾期还款金额
    private Long zfbyqTotalAmount;

    // 当前应收单量
    private Long ysNum;

    // 当前应收金额
    private Long ysTotalAmount;

    // 当前应收本金
    private Long ysbjTotalAmount;

    // 当前应收费用
    private Long ysfyTotalAmount;

    // 当前逾期单量
    private Long yqCount;

    // 当前逾期金额
    private Long yqTotalAmount;

    // 当前逾期本金
    private Long yqbjTotalAmount;

    // 当前逾期费用
    private Long yqfyTotalAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Long getRegNum() {
        return regNum;
    }

    public void setRegNum(Long regNum) {
        this.regNum = regNum;
    }

    public Long getCertiNum() {
        return certiNum;
    }

    public void setCertiNum(Long certiNum) {
        this.certiNum = certiNum;
    }

    public Long getZmxyNum() {
        return zmxyNum;
    }

    public void setZmxyNum(Long zmxyNum) {
        this.zmxyNum = zmxyNum;
    }

    public Long getCarrierNum() {
        return carrierNum;
    }

    public void setCarrierNum(Long carrierNum) {
        this.carrierNum = carrierNum;
    }

    public Long getJdtNum() {
        return jdtNum;
    }

    public void setJdtNum(Long jdtNum) {
        this.jdtNum = jdtNum;
    }

    public Long getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Long applyNum) {
        this.applyNum = applyNum;
    }

    public Long getFkNum() {
        return fkNum;
    }

    public void setFkNum(Long fkNum) {
        this.fkNum = fkNum;
    }

    public Long getFkAmount() {
        return fkAmount;
    }

    public void setFkAmount(Long fkAmount) {
        this.fkAmount = fkAmount;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getYhkNum() {
        return yhkNum;
    }

    public void setYhkNum(Long yhkNum) {
        this.yhkNum = yhkNum;
    }

    public Long getYhkAmount() {
        return yhkAmount;
    }

    public void setYhkAmount(Long yhkAmount) {
        this.yhkAmount = yhkAmount;
    }

    public Long getSjhkNum() {
        return sjhkNum;
    }

    public void setSjhkNum(Long sjhkNum) {
        this.sjhkNum = sjhkNum;
    }

    public Long getBfhkNum() {
        return bfhkNum;
    }

    public void setBfhkNum(Long bfhkNum) {
        this.bfhkNum = bfhkNum;
    }

    public Long getBfhkAmount() {
        return bfhkAmount;
    }

    public void setBfhkAmount(Long bfhkAmount) {
        this.bfhkAmount = bfhkAmount;
    }

    public Long getZfbhkNum() {
        return zfbhkNum;
    }

    public void setZfbhkNum(Long zfbhkNum) {
        this.zfbhkNum = zfbhkNum;
    }

    public Long getZfbhkAmount() {
        return zfbhkAmount;
    }

    public void setZfbhkAmount(Long zfbhkAmount) {
        this.zfbhkAmount = zfbhkAmount;
    }

    public Long getHkAmount() {
        return hkAmount;
    }

    public void setHkAmount(Long hkAmount) {
        this.hkAmount = hkAmount;
    }

    public Long getXqNum() {
        return xqNum;
    }

    public void setXqNum(Long xqNum) {
        this.xqNum = xqNum;
    }

    public Long getBfdkNum() {
        return bfdkNum;
    }

    public void setBfdkNum(Long bfdkNum) {
        this.bfdkNum = bfdkNum;
    }

    public Long getBfdkAmount() {
        return bfdkAmount;
    }

    public void setBfdkAmount(Long bfdkAmount) {
        this.bfdkAmount = bfdkAmount;
    }

    public Long getZfbdkNum() {
        return zfbdkNum;
    }

    public void setZfbdkNum(Long zfbdkNum) {
        this.zfbdkNum = zfbdkNum;
    }

    public Long getZfbdkAmount() {
        return zfbdkAmount;
    }

    public void setZfbdkAmount(Long zfbdkAmount) {
        this.zfbdkAmount = zfbdkAmount;
    }

    public Long getXqTotalAmount() {
        return xqTotalAmount;
    }

    public void setXqTotalAmount(Long xqTotalAmount) {
        this.xqTotalAmount = xqTotalAmount;
    }

    public Long getYqhkNum() {
        return yqhkNum;
    }

    public void setYqhkNum(Long yqhkNum) {
        this.yqhkNum = yqhkNum;
    }

    public Long getYqhkAmount() {
        return yqhkAmount;
    }

    public void setYqhkAmount(Long yqhkAmount) {
        this.yqhkAmount = yqhkAmount;
    }

    public Long getBfyqNum() {
        return bfyqNum;
    }

    public void setBfyqNum(Long bfyqNum) {
        this.bfyqNum = bfyqNum;
    }

    public Long getBfyqTotalAmount() {
        return bfyqTotalAmount;
    }

    public void setBfyqTotalAmount(Long bfyqTotalAmount) {
        this.bfyqTotalAmount = bfyqTotalAmount;
    }

    public Long getZfbyqNum() {
        return zfbyqNum;
    }

    public void setZfbyqNum(Long zfbyqNum) {
        this.zfbyqNum = zfbyqNum;
    }

    public Long getZfbyqTotalAmount() {
        return zfbyqTotalAmount;
    }

    public void setZfbyqTotalAmount(Long zfbyqTotalAmount) {
        this.zfbyqTotalAmount = zfbyqTotalAmount;
    }

    public Long getYsNum() {
        return ysNum;
    }

    public void setYsNum(Long ysNum) {
        this.ysNum = ysNum;
    }

    public Long getYsTotalAmount() {
        return ysTotalAmount;
    }

    public void setYsTotalAmount(Long ysTotalAmount) {
        this.ysTotalAmount = ysTotalAmount;
    }

    public Long getYsbjTotalAmount() {
        return ysbjTotalAmount;
    }

    public void setYsbjTotalAmount(Long ysbjTotalAmount) {
        this.ysbjTotalAmount = ysbjTotalAmount;
    }

    public Long getYsfyTotalAmount() {
        return ysfyTotalAmount;
    }

    public void setYsfyTotalAmount(Long ysfyTotalAmount) {
        this.ysfyTotalAmount = ysfyTotalAmount;
    }

    public Long getYqCount() {
        return yqCount;
    }

    public void setYqCount(Long yqCount) {
        this.yqCount = yqCount;
    }

    public Long getYqTotalAmount() {
        return yqTotalAmount;
    }

    public void setYqTotalAmount(Long yqTotalAmount) {
        this.yqTotalAmount = yqTotalAmount;
    }

    public Long getYqbjTotalAmount() {
        return yqbjTotalAmount;
    }

    public void setYqbjTotalAmount(Long yqbjTotalAmount) {
        this.yqbjTotalAmount = yqbjTotalAmount;
    }

    public Long getYqfyTotalAmount() {
        return yqfyTotalAmount;
    }

    public void setYqfyTotalAmount(Long yqfyTotalAmount) {
        this.yqfyTotalAmount = yqfyTotalAmount;
    }

}
