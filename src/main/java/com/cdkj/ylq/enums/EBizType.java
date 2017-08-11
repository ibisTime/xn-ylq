package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EBizType {
    AJ_GW("-30", "购物"), AJ_GWTK("30", "购物退款"), AJ_QRSH("32", "确认收货，商户收钱"),

    YC_O2O_RMB("YC_O2O_RMB", "姚橙O2O人民币支付"), YC_O2O_RMBFD("YC_O2O_RMBFD",
            "姚橙O2O人民币支付返橙券"), YC_O2O_CB("YC_O2O_CB", "姚橙O2O橙券支付"), YC_O2O_CBFD(
            "YC_O2O_CBFD", "姚橙O2O橙券支付返人民币"), YC_MALL("YC_MALL", "姚橙商城购物支付"), YC_MALL_BACK(
            "YC_MALL_BACK", "姚橙商城购物退款"), YC_XNCZ_P("YC_XNCZ_P", "姚橙充值专区支付"), YC_XNCZ_M(
            "YC_XNCZ_M", "姚橙充值专区退款"), YC_MALL_SC("YC_MALL_SC", "新产品试吃");

    public static Map<String, EBizType> getBizTypeMap() {
        Map<String, EBizType> map = new HashMap<String, EBizType>();
        for (EBizType bizType : EBizType.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EBizType(String code, String value) {
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
