package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 渠道
* @author: taojian 
* @since: 2018-11-20 12:39:57
* @history:
*/
public class Way extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 名字
    private String name;

    // 产品链接
    private String productUrl;

    // 产品链接点击数
    private Long productPointCount;

    // 链接
    private String regUrl;

    // 渠道点击数
    private Long regPointCount;

    // 注册用户数
    private Long userCount;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 公司编号
    private String companyCode;

    // 渠道商用户编号
    private String userId;

    private Wayer wayer;

    public Wayer getWayer() {
        return wayer;
    }

    public void setWayer(Wayer wayer) {
        this.wayer = wayer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Long getProductPointCount() {
        return productPointCount;
    }

    public void setProductPointCount(Long productPointCount) {
        this.productPointCount = productPointCount;
    }

    public String getRegUrl() {
        return regUrl;
    }

    public void setRegUrl(String regUrl) {
        this.regUrl = regUrl;
    }

    public Long getRegPointCount() {
        return regPointCount;
    }

    public void setRegPointCount(Long regPointCount) {
        this.regPointCount = regPointCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
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
