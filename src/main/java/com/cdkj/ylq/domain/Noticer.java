package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 通知人
* @author: taojian 
* @since: 2018-11-20 22:10:55
* @history:
*/
public class Noticer extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 名字
    private String name;

    // 手机号
    private String mobile;

    // 类型
    private String type;

    // 起始时间点
    private String startTime;

    // 结束时间点
    private String endTime;

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

    // ******************************
    // 当前时间
    private Date curDatetime;

    // 本日开始时间
    private Date dayStartTime;

    // 本日结束时间
    private Date dayEndTime;

    public Date getCurDatetime() {
        return curDatetime;
    }

    public void setCurDatetime(Date curDatetime) {
        this.curDatetime = curDatetime;
    }

    public Date getDayStartTime() {
        return dayStartTime;
    }

    public void setDayStartTime(Date dayStartTime) {
        this.dayStartTime = dayStartTime;
    }

    public Date getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(Date dayEndTime) {
        this.dayEndTime = dayEndTime;
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

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
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
