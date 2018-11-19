package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.ylq.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum ECaptchaType {
    C_REG("805041", "C端会员注册"), AG_REG("805042", "代理端会员注册"), BIND_EMAIL(
            "805081", "绑定邮箱"), ADDRESS_ADD("802170", "地址新增"), MOBILE_CHANGE(
            "805061", "更换手机验证码"), LOGIN_PWD_RESET("805063", "重置登录密码"), MODIFY_LOGIN_PWD(
            "805064", "修改登录密码"), RESET_TRADE_PWD("805067", "重置支付密码"), MODIFY_TRADE_PWD(
            "805068", "修改支付密码"), ACTIVATE_OR_LOGOFF("805084", "注销/激活用户"), C_WX_RED(
            "805051", "C端微信登录");

    public static ECaptchaType getBizType(String code) {
        Map<String, ECaptchaType> map = getBizTypeMap();
        ECaptchaType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的验证码类型不存在");
        }
        return result;
    }

    public static Map<String, ECaptchaType> getBizTypeMap() {
        Map<String, ECaptchaType> map = new HashMap<String, ECaptchaType>();
        for (ECaptchaType bizType : ECaptchaType.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    ECaptchaType(String code, String value) {
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
