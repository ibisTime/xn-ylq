package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

public enum EWayStatus {
    NORMAL("0", "正常"), Locked("1", "注销");

    EWayStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EWayStatus> getMap() {
        Map<String, EWayStatus> map = new HashMap<String, EWayStatus>();
        for (EWayStatus userStatus : EWayStatus.values()) {
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
