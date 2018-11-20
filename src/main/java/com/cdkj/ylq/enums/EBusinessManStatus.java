package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

public enum EBusinessManStatus {

    NORMAL("0", "审核通过/正常"), Locked("1", "人工锁定");

    EBusinessManStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EBusinessManStatus> getMap() {
        Map<String, EBusinessManStatus> map = new HashMap<String, EBusinessManStatus>();
        for (EBusinessManStatus userStatus : EBusinessManStatus.values()) {
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
