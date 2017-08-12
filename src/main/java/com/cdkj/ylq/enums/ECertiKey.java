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
    INFO_BASIC("INFO_BASIC", "基本信息")

    , INFO_OCCUPATION("INFO_OCCUPATION", "职业信息")

    , INFO_CONTACT("INFO_CONTACT", "紧急联系人")

    , INFO_BANKCARD("INFO_BANKCARD", "银行卡信息");

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
