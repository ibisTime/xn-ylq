package com.cdkj.ylq.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum ESysConfigType {

    QINIU("qiniu", "七牛"), PAY_RULE("pay_rule", "支付规则"), WEIXIN_H5("wx_h5", "微信h5"), 
    DIST_RULE("DIST_RULE", "分销规则"), WEIGHT("weight", "权重"), 
    CREATE_TPP("CREATE_TPP", "产生碳泡泡规则"), TPP_RULE("TPP_RULE", "碳泡泡规则"), 
    JF_RULE("JF_RULE", "积分规则"), USER_LEVEL("USER_LEVEL","用户等级");

    ESysConfigType(String code, String value) {
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
