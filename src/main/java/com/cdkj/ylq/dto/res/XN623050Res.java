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
import com.cdkj.ylq.domain.InfoBankcard;
import com.cdkj.ylq.domain.InfoBasic;
import com.cdkj.ylq.domain.InfoContact;
import com.cdkj.ylq.domain.InfoIdentify;
import com.cdkj.ylq.domain.InfoIdentifyPic;
import com.cdkj.ylq.domain.InfoOccupation;
import com.cdkj.ylq.domain.InfoZMCredit;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午1:17:23 
 * @history:
 */
public class XN623050Res {

    // 用户编号
    private String userId;

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

    // 银行卡标识
    private String infoBankcardFlag;

    // 银行卡信息
    private InfoBankcard infoBankcard;

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

    // 通讯录标识
    private String addressBookFlag;

    // 通讯录标识
    private String wechatFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getInfoBankcardFlag() {
        return infoBankcardFlag;
    }

    public void setInfoBankcardFlag(String infoBankcardFlag) {
        this.infoBankcardFlag = infoBankcardFlag;
    }

    public InfoBankcard getInfoBankcard() {
        return infoBankcard;
    }

    public void setInfoBankcard(InfoBankcard infoBankcard) {
        this.infoBankcard = infoBankcard;
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

    public String getAddressBookFlag() {
        return addressBookFlag;
    }

    public void setAddressBookFlag(String addressBookFlag) {
        this.addressBookFlag = addressBookFlag;
    }

    public String getWechatFlag() {
        return wechatFlag;
    }

    public void setWechatFlag(String wechatFlag) {
        this.wechatFlag = wechatFlag;
    }

}
