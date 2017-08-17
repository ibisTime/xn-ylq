package com.cdkj.ylq.domain;

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
    private Long amount;

    // 借款时长
    private Integer duration;

    // 7天内逾期利率
    private Double yqRate1;

    // 7天外逾期利率
    private Double yqRate2;

    // 利息利率
    private Double lxRate;

    // 快速信审费利率
    private Double xsRate;

    // 账户管理费利率
    private Double glRate;

    // 服务费利率
    private Double fwRate;

    // 状态
    private String status;

    // UI位置
    private String uiLocation;

    // UI顺序
    private Integer uiOrder;

    // UI颜色
    private String uiColor;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ******** 辅助字段 *************

    // 利息
    private Long lxAmount;

    // 快速信审费
    private Long xsAmount;

    // 账户管理费
    private Long glAmount;

    // 服务费
    private Long fwAmount;

    // 当前申请状态
    private String userProductStatus;

    // 是否锁定
    private String isLocked;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getYqRate1() {
        return yqRate1;
    }

    public void setYqRate1(Double yqRate1) {
        this.yqRate1 = yqRate1;
    }

    public Double getYqRate2() {
        return yqRate2;
    }

    public void setYqRate2(Double yqRate2) {
        this.yqRate2 = yqRate2;
    }

    public Double getLxRate() {
        return lxRate;
    }

    public void setLxRate(Double lxRate) {
        this.lxRate = lxRate;
    }

    public Double getXsRate() {
        return xsRate;
    }

    public void setXsRate(Double xsRate) {
        this.xsRate = xsRate;
    }

    public Double getGlRate() {
        return glRate;
    }

    public void setGlRate(Double glRate) {
        this.glRate = glRate;
    }

    public Double getFwRate() {
        return fwRate;
    }

    public void setFwRate(Double fwRate) {
        this.fwRate = fwRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUiLocation() {
        return uiLocation;
    }

    public void setUiLocation(String uiLocation) {
        this.uiLocation = uiLocation;
    }

    public Integer getUiOrder() {
        return uiOrder;
    }

    public void setUiOrder(Integer uiOrder) {
        this.uiOrder = uiOrder;
    }

    public String getUiColor() {
        return uiColor;
    }

    public void setUiColor(String uiColor) {
        this.uiColor = uiColor;
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

    public Long getLxAmount() {
        return lxAmount;
    }

    public void setLxAmount(Long lxAmount) {
        this.lxAmount = lxAmount;
    }

    public Long getXsAmount() {
        return xsAmount;
    }

    public void setXsAmount(Long xsAmount) {
        this.xsAmount = xsAmount;
    }

    public Long getGlAmount() {
        return glAmount;
    }

    public void setGlAmount(Long glAmount) {
        this.glAmount = glAmount;
    }

    public Long getFwAmount() {
        return fwAmount;
    }

    public void setFwAmount(Long fwAmount) {
        this.fwAmount = fwAmount;
    }

}
