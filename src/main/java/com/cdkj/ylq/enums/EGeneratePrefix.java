package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:29:02 
 * @history:
 */
public enum EGeneratePrefix {

    PRODUCT("CP", "产品"), APPLY("AP", "额度申请"), BORROW("SL", "借款"), PAY_GROUP(
            "PG", "支付组号"), REPAY_APPLY("RA", "打款申请"), RENEWAL("XQ", "续期记录"), COM(
            "GS", "公司"), UZ("UZ", "用户组"), DH("DH", "导航"), SC("SC", "公司素材"), YX(
            "YX", "合作意向"), PW("PW", "密码记录"), XX("XX", "消息"), ZZ("ZZ", "资质"), GZ(
            "GZ", "公司资质"), CM("CM", "公司菜单"), CB("CB", "公司BANNER"), AD("AD",
            "用户地址"), KQ("KQ", "卡券"), YQ("YQ", "邀请码"), Account("A", "账户"), AJour(
            "AJ", "账户流水"), Charge("C", "充值"), GS("GS", "公司"),

    Interact("I", "点赞"), BANK_CARD("BC", "银行卡"), WAY("W", "渠道"), BUSINESS(
            "JDS", "借贷商"), NOTICER("NO", "通知人"), StagingRule("SR", "分期规则");

    public static Map<String, EGeneratePrefix> getMap() {
        Map<String, EGeneratePrefix> map = new HashMap<String, EGeneratePrefix>();
        for (EGeneratePrefix orderType : EGeneratePrefix.values()) {
            map.put(orderType.getCode(), orderType);
        }
        return map;
    }

    EGeneratePrefix(String code, String value) {
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
