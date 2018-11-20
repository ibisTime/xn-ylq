package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 产品
* @author: haiqingzheng
* @since: 2017年08月11日 14:21:31
* @history:
*/
public class Product extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 名称
    private String name;

    // 广告语
    private String slogan;

    // 等级
    private String level;

    // 借款金额
    private BigDecimal amount;

    // 借款时长
    private Integer duration;

    // 7天内逾期利率
    private BigDecimal yqRate1;

    // 7天外逾期利率
    private BigDecimal yqRate2;

    // 利息利率
    private BigDecimal lxRate;

    // 快速信审费
    private BigDecimal xsAmount;

    // 账户管理费
    private BigDecimal glAmount;

    // 服务费
    private BigDecimal fwAmount;

    // 状态
    private String status;

    // UI位置
    private String location;

    // UI顺序
    private Integer orderNo;

    // UI颜色
    private String color;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 公司编号
    private String companyCode;

    // ******** 辅助字段 *************

    // 利息
    private BigDecimal lxAmount;

    // 当前申请状态
    private String userProductStatus;

    // 审核说明 - 审核不通过时返回
    private String approveNote;

    // 借款编号 - 放款后返回
    private String borrowCode;

    // 借款详情 - 放款后返回
    private Borrow borrowInfo;

    // 是否锁定
    private String isLocked;

    // 还款时间（正数代表还有几天还款，0表示当天还款，负数表示）
    private Integer hkDays;

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getYqRate1() {
        return yqRate1;
    }

    public void setYqRate1(BigDecimal yqRate1) {
        this.yqRate1 = yqRate1;
    }

    public BigDecimal getYqRate2() {
        return yqRate2;
    }

    public void setYqRate2(BigDecimal yqRate2) {
        this.yqRate2 = yqRate2;
    }

    public BigDecimal getLxRate() {
        return lxRate;
    }

    public void setLxRate(BigDecimal lxRate) {
        this.lxRate = lxRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public String getUserProductStatus() {
        return userProductStatus;
    }

    public void setUserProductStatus(String userProductStatus) {
        this.userProductStatus = userProductStatus;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public BigDecimal getLxAmount() {
        return lxAmount;
    }

    public void setLxAmount(BigDecimal lxAmount) {
        this.lxAmount = lxAmount;
    }

    public BigDecimal getXsAmount() {
        return xsAmount;
    }

    public void setXsAmount(BigDecimal xsAmount) {
        this.xsAmount = xsAmount;
    }

    public BigDecimal getGlAmount() {
        return glAmount;
    }

    public void setGlAmount(BigDecimal glAmount) {
        this.glAmount = glAmount;
    }

    public BigDecimal getFwAmount() {
        return fwAmount;
    }

    public void setFwAmount(BigDecimal fwAmount) {
        this.fwAmount = fwAmount;
    }

    public Borrow getBorrowInfo() {
        return borrowInfo;
    }

    public void setBorrowInfo(Borrow borrowInfo) {
        this.borrowInfo = borrowInfo;
    }

    public Integer getHkDays() {
        return hkDays;
    }

    public void setHkDays(Integer hkDays) {
        this.hkDays = hkDays;
    }

}
