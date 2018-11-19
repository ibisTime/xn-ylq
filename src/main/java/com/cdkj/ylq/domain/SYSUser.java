package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
 * 系统用户
 * @author: jiafr 
 * @since: 2018年9月26日 下午5:35:21 
 * @history:
 */
public class SYSUser extends ABaseDO {

    private static final long serialVersionUID = -332310384678966884L;

    // ***********db properties***********

    // 用户id
    private String userId;

    // 类型（P平台用户R产权用户M养护用户）
    private String kind;

    // 角色编号
    private String roleCode;

    // 公司编号
    private String companyCode;

    // 真实姓名
    private String realName;

    // 头像
    private String photo;

    // 电话
    private String mobile;

    // 登陆名
    private String loginName;

    // 登陆密码
    private String loginPwd;

    // 登录密码强度
    private String loginPwdStrength;

    private String isJt;

    private String isFk;

    private String isDl;

    // 创建时间
    private Date createDatetime;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ***********db properties***********

    // 注册时间起
    private Date createDatetimeStart;

    // 注册时间止
    private Date createDatetimeEnd;

    // 手机模糊查询
    private String mobileForQuery;

    // 名字模糊查询
    private String realNameForQuery;

    // 古树数量
    private String treeQuantity;

    // 古树市值
    private String treeValue;

    // 总收入
    private BigDecimal totalIncome;

    // 所属产权方
    private String owner;

    // 公司
    private Company company;

    // 古树最大市值
    private BigDecimal maxPrice;

    // 古树最小市值
    private BigDecimal minPrice;

    public String getIsJt() {
        return isJt;
    }

    public void setIsJt(String isJt) {
        this.isJt = isJt;
    }

    public String getIsFk() {
        return isFk;
    }

    public void setIsFk(String isFk) {
        this.isFk = isFk;
    }

    public String getIsDl() {
        return isDl;
    }

    public void setIsDl(String isDl) {
        this.isDl = isDl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
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

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getMobileForQuery() {
        return mobileForQuery;
    }

    public void setMobileForQuery(String mobileForQuery) {
        this.mobileForQuery = mobileForQuery;
    }

    public String getRealNameForQuery() {
        return realNameForQuery;
    }

    public void setRealNameForQuery(String realNameForQuery) {
        this.realNameForQuery = realNameForQuery;
    }

    public String getTreeQuantity() {
        return treeQuantity;
    }

    public void setTreeQuantity(String treeQuantity) {
        this.treeQuantity = treeQuantity;
    }

    public String getTreeValue() {
        return treeValue;
    }

    public void setTreeValue(String treeValue) {
        this.treeValue = treeValue;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

}
