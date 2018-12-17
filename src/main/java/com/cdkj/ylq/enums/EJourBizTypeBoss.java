package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.ylq.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EJourBizTypeBoss {
    // 人民币账户
    CHARGE("CHARGE", "充值"),

    API("API_OUT", "风控认证接口支出"),

    WITHDRAW_FEE("WITHDRAW_FEE", "取现手续费"),

    WITHDRAW_FROZEN("WITHDRAW_FROZEN", "取现冻结"),

    WITHDRAW_UNFROZEN("WITHDRAW_UNFROZEN", "取现解冻"),

    WITHDRAW("WITHDRAW", "取现");

    public static EJourBizTypeBoss getBizType(String code) {
        Map<String, EJourBizTypeBoss> map = getBizTypeMap();
        EJourBizTypeBoss result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeBoss> getBizTypeMap() {
        Map<String, EJourBizTypeBoss> map = new HashMap<String, EJourBizTypeBoss>();
        for (EJourBizTypeBoss bizType : EJourBizTypeBoss.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeBoss(String code, String value) {
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
