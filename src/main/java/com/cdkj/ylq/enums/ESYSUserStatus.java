package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESYSUserStatus {

    TO_FILL("-1", "待填写资料"), TO_APPROVE("0", "待审核"), APPROVE_NO("1", "审核不通过"), NORMAL(
            "2", "审核通过/正常"), Li_Locked("3", "程序锁定"), Ren_Locked("4", "人工锁定");

    ESYSUserStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ESYSUserStatus> getMap() {
        Map<String, ESYSUserStatus> map = new HashMap<String, ESYSUserStatus>();
        for (ESYSUserStatus userStatus : ESYSUserStatus.values()) {
            map.put(userStatus.getCode(), userStatus);
        }
        return map;
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
