/**
 * @Title User.java 
 * @Package com.ibis.pz.domain 
 * @Description 
 * @author miyb  
 * @date 2015-2-6 上午10:31:01 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

import com.cdkj.ylq.dao.base.ABaseDO;

/** 
 * @author: miyb 
 * @since: 2015-2-6 上午10:31:01 
 * @history:
 */
public class User extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1975331351390818527L;

    // userId
    private String userId;

    // openId
    private String openId;

    // kind
    private String kind;

    // 登陆名
    private String loginName;

    // 昵称
    private String nickname;

    // 头像
    private String photo;

    // 手机号
    private String mobile;

    // 是否实名认证
    private String identityFlag;

    // 证件类型
    private String idKind;

    // 证件号码
    private String idNo;

    // 真实姓名
    private String realName;

    // 推荐人
    private String userReferee;

    // 分成比例
    private Double divRate;

    // 省
    private String province;

    // 市区
    private String city;

    // 区(县)
    private String area;

    // 公司编号
    private String companyCode;

    // 系统编号
    private String systemCode;

    // 是否被拉入黑名单
    private String blacklistFlag;

    // 是否绑定银行卡
    private String bankcardFlag;

    // 借款次数
    private Integer borrowCount;

    // 逾期代码
    private String overdueCode;

    // 续期次数
    private Integer renewalCount;

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBlacklistFlag() {
        return blacklistFlag;
    }

    public void setBlacklistFlag(String blacklistFlag) {
        this.blacklistFlag = blacklistFlag;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Double getDivRate() {
        return divRate;
    }

    public void setDivRate(Double divRate) {
        this.divRate = divRate;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getIdentityFlag() {
        return identityFlag;
    }

    public void setIdentityFlag(String identityFlag) {
        this.identityFlag = identityFlag;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public String getBankcardFlag() {
        return bankcardFlag;
    }

    public void setBankcardFlag(String bankcardFlag) {
        this.bankcardFlag = bankcardFlag;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public String getOverdueCode() {
        return overdueCode;
    }

    public void setOverdueCode(String overdueCode) {
        this.overdueCode = overdueCode;
    }

    public Integer getRenewalCount() {
        return renewalCount;
    }

    public void setRenewalCount(Integer renewalCount) {
        this.renewalCount = renewalCount;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
