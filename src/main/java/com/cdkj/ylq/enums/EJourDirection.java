package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.ylq.exception.BizException;

/**
 * 流水流向
 * @author: silver 
 * @since: Oct 19, 2018 11:01:48 AM 
 * @history:
 */
public enum EJourDirection {

    IN("0", "流入"), OUT("1", "流出");

    public static Map<String, EJourDirection> getJourKindResultMap() {
        Map<String, EJourDirection> map = new HashMap<String, EJourDirection>();
        for (EJourDirection type : EJourDirection.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EJourDirection getJourKind(String code) {
        Map<String, EJourDirection> map = getJourKindResultMap();
        EJourDirection result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的EJourKind不存在");
        }
        return result;
    }

    EJourDirection(String code, String value) {
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
