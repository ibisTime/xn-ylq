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
    CHARGE("charge", "充值"),

    WITHDRAW_FEE("withdraw_fee", "取现手续费"),

    WITHDRAW_TRANS_FEE("withdraw_trans_fee", "取现转账费"),

    WITHDRAW_ENTER("withdraw_enter", "提现回录"),

    ADOPT("adopt", "认养"),

    ADOPT_COLLECT("adopt_collect", "集体认养"),

    UN_FULL_CNY("un_full_cny", "集体认养未满标退款"),

    ADOPT_DIST("adopt_dist", "认养分成"),

    ADOPT_PROFIT("adopt_profit", "认养收益"),

    COMMODITY("commodity", "商城购买"),

    COMMODITY_DIST("commodity_dist", "商城购买分成"),

    HC("hc", "红冲"),

    LB("lb", "蓝补"),

    HAND_CHARGE("hand_charge", "手动增发"),

    PRESELL("presell", "购买预售"),
    // 碳泡泡账户
    ADOPT_DAY_BACK("adopt_day_back", "认养消费每日收取碳泡泡"),

    SHARE("share", "分享"),

    PRESENT("present", "赠送"), SIGN("sign", "签到"),

    // 积分账户
    REGIST("reg", "注册送积分"),

    BIND_MOBILE("bind_mobile", "绑定手机"),

    BIND_email("bind_email", "绑定邮箱"),

    UPLOAD_PHOTO("upload_photo", "上传头像"),

    COMPLETE_INFO("complete_info", "完善用户信息"),

    LOGIN("login", "登录"),

    INVITE_USER("invite_user", "邀请好友注册"),

    TOOL_BUY("tool_buy", "购买道具"),

    ADOPT_BUY_DEDUCT("adopt_buy_deduct", "认养抵扣"),

    COMMODITY_BUY_DEDUCT("adopt_buy_deduct", "商城抵扣"),

    ADOPT_PAY_BACK("adopt_pay_back", "认养消费返利"),

    UN_FULL_DEDUCTJF("un_full_deductjf", "集体认养未满标退抵扣积分"),

    UN_FULL_BACKJF("un_full_backjf", "集体认养未满标退返利积分");

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
