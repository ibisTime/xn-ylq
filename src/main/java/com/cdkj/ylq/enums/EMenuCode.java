package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2017年2月9日 下午8:10:43 
 * @history:
 */
public enum EMenuCode {
    borrow("SM201707261741263791893", "借条菜单编号"),

    way("SM2018120117265880380149", "导流菜单编号"),

    risk("SM2018120604520596132802", "风控菜单编号");
    public static Map<String, EMenuCode> getMap() {
        Map<String, EMenuCode> map = new HashMap<String, EMenuCode>();
        for (EMenuCode direction : EMenuCode.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    EMenuCode(String code, String value) {
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
