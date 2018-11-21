/**
 * @Title ECertiKey.java 
 * @Package com.cdkj.ylq.enums 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 上午11:56:22 
 * @version V1.0   
 */
package com.cdkj.ylq.enums;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 上午11:56:22 
 * @history:
 */
public enum ECertiKey {
    INFO_IDENTIFY("INFO_IDENTIFY", "实名认证提交")

    , INFO_IDENTIFY_PIC("INFO_IDENTIFY_PIC", "身份证照片")

    , INFO_ZHIFUBAO("INFO_ZHIFUBAO", "支付宝信息")

    , INFO_IDENTIFY_FACE("INFO_IDENTIFY_FACE", "人脸识别")

    , INFO_BASIC("INFO_BASIC", "基本信息")

    , INFO_OCCUPATION("INFO_OCCUPATION", "职业信息")

    , INFO_CONTACT("INFO_CONTACT", "紧急联系人")

    , INFO_ANTIFRAUD("INFO_ANTIFRAUD", "欺诈信息")

    , INFO_ZMCREDIT("INFO_ZMCREDIT", "芝麻信用认证")

    , INFO_CARRIER("INFO_CARRIER", "运营商认证")

    , INFO_AMOUNT("INFO_AMOUNT", "授信额度")

    // 可选认证
    , INFO_ADDRESS_BOOK("INFO_ADDRESS_BOOK", "通讯录认证")

    // 同盾贷前审核报告
    , INFO_TONGDUN_PRELOAN("INFO_TONGDUN_PRELOAN", "同盾贷前审核报告");

    ECertiKey(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
