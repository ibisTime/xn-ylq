package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 借款
* @author: haiqingzheng
* @since: 2017年08月16日 16:39:48
* @history:
*/
public class BorrowOrder extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型
    private String type;

    // 申请人
    private String applyUser;

    // 签约时间
    private Date signDatetime;

    // 借款金额
    private BigDecimal amount;

    // 等级
    private String level;

    // 借款时长
    private Integer duration;

    // 放款时间
    private Date fkDatetime;

    // 计息时间
    private Date jxDatetime;

    // 约定还款时间
    private Date hkDatetime;

    // 利息利率
    private BigDecimal lxRate;

    // 正常应付利息
    private BigDecimal lxAmount;

    // 快速信审费
    private BigDecimal xsAmount;

    // 账户管理费
    private BigDecimal glAmount;

    // 服务费
    private BigDecimal fwAmount;

    // 优惠金额
    private BigDecimal yhAmount;

    // 7天内逾期利率
    private BigDecimal rate1;

    // 7天外逾期利率
    private BigDecimal rate2;

    // 逾期利息
    private BigDecimal yqlxAmount;

    // 逾期天数
    private Integer yqDays;

    // 共计应还金额
    private BigDecimal totalAmount;

    // 实际还款时间
    private Date realHkDatetime;

    // 实际还款金额
    private BigDecimal realHkAmount;

    // 支付编号
    private String payCode;

    // 支付组号
    private String payGroup;

    // 还款方式
    private String payType;

    // 放款方式
    private String loanType;

    // 是否分期
    private String isStage;

    // 分期次数
    private Integer stageBatch;

    // 本次分期期数
    private Long stageCount;

    // 本次分期周期
    private Long stageCycle;

    // 已还期数
    private Long repayCount;

    // 状态
    private String status;

    // 是否归档
    private String isArchive;

    // 是否计入优惠券
    private String isCoupon;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // 公司编号
    private String companyCode;

    // *** 辅助字段 ****

    // 用户信息
    private User user;

    // 银行卡信息
    private Bankcard bankcard;

    // 还款剩余天数
    private Integer remainDays;

    // *** 查询字段 ****
    // 状态列表
    private List<String> statusList;

    // 当前时间
    private Date curDatetime;

    // 编号模糊查询
    private String codeForQuery;

    // 逾期天数起
    private Integer yqDaysStart;

    // 逾期天数止
    private Integer yqDaysEnd;

    // 是否逾期
    private String isOverdue;

    // 分期列表
    private List<StageInfo> stageList;

    // 当天分期还款信息
    private StageInfo info;

    // 借款次数
    private Integer borrowCount;

    // 逾期次数
    private Integer yqCount;

    public Integer getYqCount() {
        return yqCount;
    }

    public void setYqCount(Integer yqCount) {
        this.yqCount = yqCount;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public StageInfo getInfo() {
        return info;
    }

    public void setInfo(StageInfo info) {
        this.info = info;
    }

    public List<StageInfo> getStageList() {
        return stageList;
    }

    public void setStageList(List<StageInfo> stageList) {
        this.stageList = stageList;
    }

    public String getIsStage() {
        return isStage;
    }

    public void setIsStage(String isStage) {
        this.isStage = isStage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Long getRepayCount() {
        return repayCount;
    }

    public void setRepayCount(Long repayCount) {
        this.repayCount = repayCount;
    }

    public Date getSignDatetime() {
        return signDatetime;
    }

    public void setSignDatetime(Date signDatetime) {
        this.signDatetime = signDatetime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getFkDatetime() {
        return fkDatetime;
    }

    public void setFkDatetime(Date fkDatetime) {
        this.fkDatetime = fkDatetime;
    }

    public Date getJxDatetime() {
        return jxDatetime;
    }

    public void setJxDatetime(Date jxDatetime) {
        this.jxDatetime = jxDatetime;
    }

    public Date getHkDatetime() {
        return hkDatetime;
    }

    public void setHkDatetime(Date hkDatetime) {
        this.hkDatetime = hkDatetime;
    }

    public BigDecimal getLxRate() {
        return lxRate;
    }

    public void setLxRate(BigDecimal lxRate) {
        this.lxRate = lxRate;
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

    public BigDecimal getYhAmount() {
        return yhAmount;
    }

    public void setYhAmount(BigDecimal yhAmount) {
        this.yhAmount = yhAmount;
    }

    public BigDecimal getRate1() {
        return rate1;
    }

    public void setRate1(BigDecimal rate1) {
        this.rate1 = rate1;
    }

    public BigDecimal getRate2() {
        return rate2;
    }

    public void setRate2(BigDecimal rate2) {
        this.rate2 = rate2;
    }

    public BigDecimal getYqlxAmount() {
        return yqlxAmount;
    }

    public void setYqlxAmount(BigDecimal yqlxAmount) {
        this.yqlxAmount = yqlxAmount;
    }

    public Integer getStageBatch() {
        return stageBatch;
    }

    public void setStageBatch(Integer stageBatch) {
        this.stageBatch = stageBatch;
    }

    public Integer getYqDays() {
        return yqDays;
    }

    public void setYqDays(Integer yqDays) {
        this.yqDays = yqDays;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getRealHkDatetime() {
        return realHkDatetime;
    }

    public void setRealHkDatetime(Date realHkDatetime) {
        this.realHkDatetime = realHkDatetime;
    }

    public BigDecimal getRealHkAmount() {
        return realHkAmount;
    }

    public void setRealHkAmount(BigDecimal realHkAmount) {
        this.realHkAmount = realHkAmount;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Long getStageCount() {
        return stageCount;
    }

    public void setStageCount(Long stageCount) {
        this.stageCount = stageCount;
    }

    public Long getStageCycle() {
        return stageCycle;
    }

    public void setStageCycle(Long stageCycle) {
        this.stageCycle = stageCycle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(String isArchive) {
        this.isArchive = isArchive;
    }

    public String getIsCoupon() {
        return isCoupon;
    }

    public void setIsCoupon(String isCoupon) {
        this.isCoupon = isCoupon;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bankcard getBankcard() {
        return bankcard;
    }

    public void setBankcard(Bankcard bankcard) {
        this.bankcard = bankcard;
    }

    public Integer getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(Integer remainDays) {
        this.remainDays = remainDays;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public Date getCurDatetime() {
        return curDatetime;
    }

    public void setCurDatetime(Date curDatetime) {
        this.curDatetime = curDatetime;
    }

    public String getCodeForQuery() {
        return codeForQuery;
    }

    public void setCodeForQuery(String codeForQuery) {
        this.codeForQuery = codeForQuery;
    }

    public Integer getYqDaysStart() {
        return yqDaysStart;
    }

    public void setYqDaysStart(Integer yqDaysStart) {
        this.yqDaysStart = yqDaysStart;
    }

    public Integer getYqDaysEnd() {
        return yqDaysEnd;
    }

    public void setYqDaysEnd(Integer yqDaysEnd) {
        this.yqDaysEnd = yqDaysEnd;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
