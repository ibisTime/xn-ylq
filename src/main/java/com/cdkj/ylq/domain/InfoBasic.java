/**
 * @Title InfoBasic.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午12:22:50 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午12:22:50 
 * @history:
 */
public class InfoBasic {
    // 学历
    private String education;

    // 婚姻
    private String marriage;

    // 子女个数
    private Integer childrenNum;

    // 居住省市
    private String provinceCity;

    // 常住地址
    private String address;

    // 居住时长
    private String liveTime;

    // QQ
    private String qq;

    // 邮箱
    private String email;

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

    public Integer getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(Integer childrenNum) {
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
