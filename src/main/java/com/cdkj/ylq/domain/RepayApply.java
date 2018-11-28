package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 线下打款申请
* @author: haiqingzheng
* @since: 2017年09月05日 18:17:10
* @history:
*/
public class RepayApply extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 针对借款编号
    private String refNo;

    // 打款类型
    private String type;

    // 打款金额
    private BigDecimal amount;

    // 申请人
    private String applyUser;

    // 申请备注
    private String applyNote;

    // 申请时间
    private Date applyDatetime;

    // 审核人
    private String approver;

    // 审核说明
    private String approveNote;

    // 审核时间
    private Date approveDatetime;

    // 状态
    private String status;

    // 公司编号
    private String companyCode;

    // ****查询字段******

    // 订单编号模糊查询
    private String codeForQuery;

    // 关联订单号模糊查询
    private String refNoForQuery;

    // ****辅助字段******

    private User user;

    private BorrowOrder borrow;

    private Renewal renewal;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
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

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodeForQuery() {
        return codeForQuery;
    }

    public void setCodeForQuery(String codeForQuery) {
        this.codeForQuery = codeForQuery;
    }

    public BorrowOrder getBorrow() {
        return borrow;
    }

    public void setBorrow(BorrowOrder borrow) {
        this.borrow = borrow;
    }

    public Renewal getRenewal() {
        return renewal;
    }

    public void setRenewal(Renewal renewal) {
        this.renewal = renewal;
    }

    public String getRefNoForQuery() {
        return refNoForQuery;
    }

    public void setRefNoForQuery(String refNoForQuery) {
        this.refNoForQuery = refNoForQuery;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
