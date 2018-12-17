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
public enum EOutFeeKey {

    INFO_DT_REPORT("INFO_DT_REPORT_FEE", "多头报告")

    , INFO_ZQZN("INFO_ZQZN_FEE", "智趣智能活体识别")

    , INFO_ZHIFUBAO("INFO_ZHIFUBAO_FEE", "支付宝信息")//

    , INFO_CARRIER("INFO_CARRIER_FEE", "运营商认证");

    public static Map<String, EOutFeeKey> getCertiKeyMap() {
        Map<String, EOutFeeKey> map = new HashMap<String, EOutFeeKey>();
        for (EOutFeeKey key : EOutFeeKey.values()) {
            map.put(key.getCode(), key);
        }
        return map;
    }

    EOutFeeKey(String code, String value) {
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
