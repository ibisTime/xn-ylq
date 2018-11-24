/**
 * @Title XN623050Res.java 
 * @Package com.cdkj.ylq.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午1:17:23 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.res;

import com.cdkj.ylq.domain.InfoAntifraud;
import com.cdkj.ylq.domain.InfoBasic;
import com.cdkj.ylq.domain.InfoContact;
import com.cdkj.ylq.domain.InfoIdentify;
import com.cdkj.ylq.domain.InfoIdentifyPic;
import com.cdkj.ylq.domain.InfoOccupation;
import com.cdkj.ylq.domain.InfoTongDunPreLoan;
import com.cdkj.ylq.domain.InfoZMCredit;
import com.cdkj.ylq.domain.InfoZfb;
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

    // 身份证上传标识
    private String infoIdentifyPicFlag;

    // 身份证照片信息
    private InfoIdentifyPic infoIdentifyPic;

    // 人脸标识标识
    private String infoIdentifyFaceFlag;

    // 实名认证信息
    private InfoIdentify infoIdentifyFace;

    // 身份认证标识
    private String infoIdentifyFlag;

    // 实名认证信息
    private InfoIdentify infoIdentify;

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

    // 欺诈信息标识
    private String infoAntifraudFlag;

    // 欺诈信息
    private InfoAntifraud infoAntifraud;

    // 芝麻信用标识
    private String infoZMCreditFlag;

    // 芝麻信用结果
    private InfoZMCredit infoZMCredit;

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

    // 同盾贷前审核报告标识
    private String infoTongDunPreLoanFlag;

    // 同盾贷前审核报告
    private InfoTongDunPreLoan infoTongDunPreLoan;

    // 支付宝标示
    private String infoZfbFlag;

    // 支付宝报告
    private InfoZfb infoZfb;

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

    public String getInfoTongDunPreLoanFlag() {
        return infoTongDunPreLoanFlag;
    }

    public void setInfoTongDunPreLoanFlag(String infoTongDunPreLoanFlag) {
        this.infoTongDunPreLoanFlag = infoTongDunPreLoanFlag;
    }

    public InfoTongDunPreLoan getInfoTongDunPreLoan() {
        return infoTongDunPreLoan;
    }

    public void setInfoTongDunPreLoan(InfoTongDunPreLoan infoTongDunPreLoan) {
        this.infoTongDunPreLoan = infoTongDunPreLoan;
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

    public String getInfoIdentifyPicFlag() {
        return infoIdentifyPicFlag;
    }

    public void setInfoIdentifyPicFlag(String infoIdentifyPicFlag) {
        this.infoIdentifyPicFlag = infoIdentifyPicFlag;
    }

    public InfoIdentifyPic getInfoIdentifyPic() {
        return infoIdentifyPic;
    }

    public void setInfoIdentifyPic(InfoIdentifyPic infoIdentifyPic) {
        this.infoIdentifyPic = infoIdentifyPic;
    }

    public String getInfoIdentifyFaceFlag() {
        return infoIdentifyFaceFlag;
    }

    public void setInfoIdentifyFaceFlag(String infoIdentifyFaceFlag) {
        this.infoIdentifyFaceFlag = infoIdentifyFaceFlag;
    }

    public InfoIdentify getInfoIdentifyFace() {
        return infoIdentifyFace;
    }

    public void setInfoIdentifyFace(InfoIdentify infoIdentifyFace) {
        this.infoIdentifyFace = infoIdentifyFace;
    }

    public String getInfoIdentifyFlag() {
        return infoIdentifyFlag;
    }

    public void setInfoIdentifyFlag(String infoIdentifyFlag) {
        this.infoIdentifyFlag = infoIdentifyFlag;
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

    public String getInfoAntifraudFlag() {
        return infoAntifraudFlag;
    }

    public void setInfoAntifraudFlag(String infoAntifraudFlag) {
        this.infoAntifraudFlag = infoAntifraudFlag;
    }

    public InfoAntifraud getInfoAntifraud() {
        return infoAntifraud;
    }

    public void setInfoAntifraud(InfoAntifraud infoAntifraud) {
        this.infoAntifraud = infoAntifraud;
    }

    public String getInfoZMCreditFlag() {
        return infoZMCreditFlag;
    }

    public void setInfoZMCreditFlag(String infoZMCreditFlag) {
        this.infoZMCreditFlag = infoZMCreditFlag;
    }

    public InfoZMCredit getInfoZMCredit() {
        return infoZMCredit;
    }

    public void setInfoZMCredit(InfoZMCredit infoZMCredit) {
        this.infoZMCredit = infoZMCredit;
    }

    public InfoIdentify getInfoIdentify() {
        return infoIdentify;
    }

    public void setInfoIdentify(InfoIdentify infoIdentify) {
        this.infoIdentify = infoIdentify;
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
