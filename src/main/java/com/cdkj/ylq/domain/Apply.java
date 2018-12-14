package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 额度申请
* @author: haiqingzheng
* @since: 2017年08月11日 19:55:22
* @history:
*/
public class Apply extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 申请人
    private String applyUser;

    // 申请时间
    private Date applyDatetime;

    // 申请说明
    private String applyNote;

    // 状态
    private String status;

    // 授信额度
    private BigDecimal creditScore;

    // 审核人
    private String approver;

    // 审核说明
    private String approveNote;

    // 审核时间
    private Date approveDatetime;

    // 当前节点
    private String curNode;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 借贷通报告
    private String report;

    // 公司编号
    private String companyCode;

    // *** 辅助字段 ****

    private User user;

    private Product product;

    // *** 查询字段 ****
    // 状态列表
    private List<String> statusList;

    // 已认证节点
    private List<Certification> certifications;

    // 借款次数
    private Integer borrowCount;

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public String getCurNode() {
        return curNode;
    }

    public void setCurNode(String curNode) {
        this.curNode = curNode;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public BigDecimal getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(BigDecimal creditScore) {
        this.creditScore = creditScore;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

}
