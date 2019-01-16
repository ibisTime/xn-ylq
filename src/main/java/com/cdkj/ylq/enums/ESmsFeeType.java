package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.ylq.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum ESmsFeeType {
    PLATDXFEE("PLATDXFEE", "平台短信费"), BOSSDXFEE("BOSSDXFEE", "boss短信费");

    public static Map<String, ESmsFeeType> getAccountTypeResultMap() {
        Map<String, ESmsFeeType> map = new HashMap<String, ESmsFeeType>();
        for (ESmsFeeType type : ESmsFeeType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static ESmsFeeType getAccountType(String code) {
        Map<String, ESmsFeeType> map = getAccountTypeResultMap();
        ESmsFeeType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的accountType不存在");
        }
        return result;
    }

    ESmsFeeType(String code, String value) {
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
