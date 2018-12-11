/**
 * @Title ECertiKey.java 
 * @Package com.cdkj.ylq.enums 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 上午11:56:22 
 * @version V1.0   
 */
package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 上午11:56:22 
 * @history:
 */
public enum ECertiKey {

    INFO_DT_REPORT("INFO_DT_REPORT", "多头报告")

    , INFO_ZQZN("INFO_ZQZN", "智趣智能活体识别")

    , INFO_ZHIFUBAO("INFO_ZHIFUBAO", "支付宝信息")//

    , INFO_BASIC("INFO_BASIC", "基本信息")//

    , INFO_OCCUPATION("INFO_OCCUPATION", "职业信息")//

    , INFO_CONTACT("INFO_CONTACT", "紧急联系人")//

    , INFO_PERSONAL("INFO_PERSONAL", "个人基本信息")//

    , INFO_CARRIER("INFO_CARRIER", "运营商认证")

    , INFO_ADDRESS_BOOK("INFO_ADDRESS_BOOK", "通讯录认证")//

    , INFO_AMOUNT("INFO_AMOUNT", "授信额度");

    public static Map<String, ECertiKey> getCertiKeyMap() {
        Map<String, ECertiKey> map = new HashMap<String, ECertiKey>();
        for (ECertiKey key : ECertiKey.values()) {
            map.put(key.getCode(), key);
        }
        return map;
    }

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
