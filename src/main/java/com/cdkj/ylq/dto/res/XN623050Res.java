/**
 * @Title XN623050Res.java 
 * @Package com.cdkj.ylq.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午1:17:23 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.res;

import com.cdkj.ylq.domain.InfoBasic;
import com.cdkj.ylq.domain.InfoContact;
import com.cdkj.ylq.domain.InfoOccupation;
import com.cdkj.ylq.domain.InfoZfb;
import com.cdkj.ylq.domain.InfoZqzn;
import com.cdkj.ylq.domain.User;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午1:17:23 
 * @history:
 */
public class XN623050Res {

    // 用户编号
    private String userId;

    // 用户信息
    private User userInfo;

    // 基本信息标识
    private String infoBasicFlag;

    // 基本信息
    private InfoBasic infoBasic;

    // 职业信息标识
    private String infoOccupationFlag;

    // 职业信息
    private InfoOccupation infoOccupation;

    // 紧急联系人标识
    private String infoContactFlag;

    // 紧急联系人信息
    private InfoContact infoContact;

    // 运营商标识
    private String infoCarrierFlag;

    // 运营商报告
    private String infoCarrier;

    // 通讯录标识
    private String infoAddressBookFlag;

    // 通讯录列表
    private String infoAddressBook;

    // 通讯录标识
    private String wechatFlag;

    // 支付宝标示
    private String infoZfbFlag;

    // 支付宝报告
    private InfoZfb infoZfb;

    //
    private String infoZqznFlag;

    private InfoZqzn infoZqzn;

    private String infoPersonalFlag;

    public String getInfoPersonalFlag() {
        return infoPersonalFlag;
    }

    public void setInfoPersonalFlag(String infoPersonalFlag) {
        this.infoPersonalFlag = infoPersonalFlag;
    }

    public String getInfoZqznFlag() {
        return infoZqznFlag;
    }

    public void setInfoZqznFlag(String infoZqznFlag) {
        this.infoZqznFlag = infoZqznFlag;
    }

    public InfoZqzn getInfoZqzn() {
        return infoZqzn;
    }

    public void setInfoZqzn(InfoZqzn infoZqzn) {
        this.infoZqzn = infoZqzn;
    }

    public String getInfoZfbFlag() {
        return infoZfbFlag;
    }

    public void setInfoZfbFlag(String infoZfbFlag) {
        this.infoZfbFlag = infoZfbFlag;
    }

    public InfoZfb getInfoZfb() {
        return infoZfb;
    }

    public void setInfoZfb(InfoZfb infoZfb) {
        this.infoZfb = infoZfb;
    }

    public String getInfoCarrier() {
        return infoCarrier;
    }

    public void setInfoCarrier(String infoCarrier) {
        this.infoCarrier = infoCarrier;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public String getInfoBasicFlag() {
        return infoBasicFlag;
    }

    public void setInfoBasicFlag(String infoBasicFlag) {
        this.infoBasicFlag = infoBasicFlag;
    }

    public InfoBasic getInfoBasic() {
        return infoBasic;
    }

    public void setInfoBasic(InfoBasic infoBasic) {
        this.infoBasic = infoBasic;
    }

    public String getInfoOccupationFlag() {
        return infoOccupationFlag;
    }

    public void setInfoOccupationFlag(String infoOccupationFlag) {
        this.infoOccupationFlag = infoOccupationFlag;
    }

    public InfoOccupation getInfoOccupation() {
        return infoOccupation;
    }

    public void setInfoOccupation(InfoOccupation infoOccupation) {
        this.infoOccupation = infoOccupation;
    }

    public String getInfoContactFlag() {
        return infoContactFlag;
    }

    public void setInfoContactFlag(String infoContactFlag) {
        this.infoContactFlag = infoContactFlag;
    }

    public InfoContact getInfoContact() {
        return infoContact;
    }

    public void setInfoContact(InfoContact infoContact) {
        this.infoContact = infoContact;
    }

    public String getInfoCarrierFlag() {
        return infoCarrierFlag;
    }

    public void setInfoCarrierFlag(String infoCarrierFlag) {
        this.infoCarrierFlag = infoCarrierFlag;
    }

    public String getInfoAddressBookFlag() {
        return infoAddressBookFlag;
    }

    public void setInfoAddressBookFlag(String infoAddressBookFlag) {
        this.infoAddressBookFlag = infoAddressBookFlag;
    }

    public String getInfoAddressBook() {
        return infoAddressBook;
    }

    public void setInfoAddressBook(String infoAddressBook) {
        this.infoAddressBook = infoAddressBook;
    }

    public String getWechatFlag() {
        return wechatFlag;
    }

    public void setWechatFlag(String wechatFlag) {
        this.wechatFlag = wechatFlag;
    }

}
