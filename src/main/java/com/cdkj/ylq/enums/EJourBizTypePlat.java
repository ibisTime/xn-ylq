package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.ylq.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EJourBizTypePlat {
    // 人民币账户

    REPORT("REPORT", "报告收入"),

    WITHDRAW_FEE("WITHDRAW_FEE", "取现手续费"),

    API("API", "征信接口支出");

    public static EJourBizTypePlat getBizType(String code) {
        Map<String, EJourBizTypePlat> map = getBizTypeMap();
        EJourBizTypePlat result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypePlat> getBizTypeMap() {
        Map<String, EJourBizTypePlat> map = new HashMap<String, EJourBizTypePlat>();
        for (EJourBizTypePlat bizType : EJourBizTypePlat.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypePlat(String code, String value) {
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
