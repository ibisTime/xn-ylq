package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 个人报告
* @author: haiqingzheng
* @since: 2017年08月02日 15:35:58
* @history:
*/
public class MxReportData extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID主键
    private Long id;

    // 用户id，创建任务时的userid
    private String userId;

    // 手机号码
    private String mobile;

    // 任务id
    private String taskId;

    // 报告数据
    private String reportData;

    // 创建时间
    private Date createTime;

    // 最后修改时间
    private Date lastModifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getReportData() {
        return reportData;
    }

    public void setReportData(String reportData) {
        this.reportData = reportData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

}
