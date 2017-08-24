package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 用户优惠券
* @author: haiqingzheng
* @since: 2017年08月16日 13:51:41
* @history:
*/
public class UserCoupon extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private Long id;

    // 用户编号
    private String userId;

    // 获得时间
    private Date getDatetime;

    // 优惠券类型
    private String type;

    // 优惠券金额
    private Long amount;

    // 起用金额
    private Long startAmount;

    // 有效天数
    private Integer validDays;

    // 失效时间
    private Date invalidDatetime;

    // 状态
    private String status;

    // 关联借款编号
    private String borrowCode;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ****** 查询字段 ******

    // 当前产品可借金额
    private Long productAmount;

    // 当前时间
    private Date curDatetime;

    // ****** 辅助字段 ******
    // 用户信息
    private User user;

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

    public Date getGetDatetime() {
        return getDatetime;
    }

    public void setGetDatetime(Date getDatetime) {
        this.getDatetime = getDatetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(Long startAmount) {
        this.startAmount = startAmount;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Date getInvalidDatetime() {
        return invalidDatetime;
    }

    public void setInvalidDatetime(Date invalidDatetime) {
        this.invalidDatetime = invalidDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
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

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }

    public Date getCurDatetime() {
        return curDatetime;
    }

    public void setCurDatetime(Date curDatetime) {
        this.curDatetime = curDatetime;
    }

}
