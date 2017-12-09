package com.cdkj.ylq.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EPayType {
    YE("1", "余额"), WEIXIN_APP("2", "微信APP"), ALIPAY("3", "支付宝"), OFFLINE("4",
            "线下"), BAOFOO_WITHHOLD_AUTO("5", "宝付银行卡代扣（自动）"), BAOFOO_WITHHOLD_USER(
            "6", "宝付银行卡代扣（客户）"), BAOFOO_WITHHOLD_OSS("7", "宝付银行卡代扣（平台）");

    EPayType(String code, String value) {
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
