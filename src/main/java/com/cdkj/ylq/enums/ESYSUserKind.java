package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.ylq.exception.BizException;

/**
 * 系统用户类型
 * @author: jiafr 
 * @since: 2018年9月28日 下午5:03:13 
 * @history:
 */
public enum ESYSUserKind {
    PLAT("P", "平台方用户"), OWNER("O", "产权方用户"), MAINTAIN("M", "养护方用户"), BUSINESS(
            "B", "商家");

    public static Map<String, ESYSUserKind> getAccountTypeResultMap() {
        Map<String, ESYSUserKind> map = new HashMap<String, ESYSUserKind>();
        for (ESYSUserKind type : ESYSUserKind.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static ESYSUserKind getAccountType(String code) {
        Map<String, ESYSUserKind> map = getAccountTypeResultMap();
        ESYSUserKind result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的accountType不存在");
        }
        return result;
    }

    ESYSUserKind(String code, String value) {
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
