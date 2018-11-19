package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.ylq.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum EJourType {

    BALANCE("0", "余额流水"), FROZEN("1", "冻结金额流水");

    public static Map<String, EJourType> getJourKindResultMap() {
        Map<String, EJourType> map = new HashMap<String, EJourType>();
        for (EJourType type : EJourType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EJourType getJourKind(String code) {
        Map<String, EJourType> map = getJourKindResultMap();
        EJourType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的EJourKind不存在");
        }
        return result;
    }

    EJourType(String code, String value) {
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
