/**
 * @Title XN623040Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午12:10:35 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午12:10:35 
 * @history:
 */
public class XN623040Req {

    // 用户编号（必填）
    private String userId;

    // 学历（必填）
    private String education;

    // 婚姻（必填）
    private String marriage;

    // 子女个数（必填）
    private String childrenNum;

    // 居住省市（必填）
    private String provinceCity;

    // 常住地址（必填）
    private String address;

    // 居住时长（必填）
    private String liveTime;

    // QQ（必填）
    private String wechat;

    // 邮箱（必填）
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(String childrenNum) {
        this.childrenNum = childrenNum;
    }

    public String getProvinceCity() {
        return provinceCity;
    }

    public void setProvinceCity(String provinceCity) {
        this.provinceCity = provinceCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
