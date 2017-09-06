package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 续期记录
* @author: haiqingzheng
* @since: 2017年09月06日 16:43:39
* @history:
*/
public class Renewal extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 申请人
    private String applyUser;

    // 借款编号
    private String borrowCode;

    // 续期步长
    private Integer step;

    // 续期周期
    private Integer cycle;

    // 开始时间
    private Date startDate;

    // 结束时间
    private Date endDate;

    // 续期前逾期费
    private Long yqAmount;

    // 快速信审费
    private Long xsAmount;

    // 账户管理费
    private Long glAmount;

    // 服务费
    private Long fwAmount;

    // 利息
    private Long lxAmount;

    // 续费总费用
    private Long totalAmount;

    // 支付时间
    private Date payDatetime;

    // 支付方式
    private String payType;

    // 三方支付编号
    private String payCode;

    // 支付组号
    private String payGroup;

    // 创建时间
    private Date createDatetime;

    // 状态
    private String status;

    // 第几次续期
    private Integer curNo;

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

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getYqAmount() {
        return yqAmount;
    }

    public void setYqAmount(Long yqAmount) {
        this.yqAmount = yqAmount;
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

    public Long getLxAmount() {
        return lxAmount;
    }

    public void setLxAmount(Long lxAmount) {
        this.lxAmount = lxAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCurNo() {
        return curNo;
    }

    public void setCurNo(Integer curNo) {
        this.curNo = curNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
