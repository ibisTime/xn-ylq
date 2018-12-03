package com.cdkj.ylq.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2017年2月9日 下午8:10:43 
 * @history:
 */
public enum ECompanyCodeModel {
    MODEL("GSModelCode", "模版公司编号");
    public static Map<String, ECompanyCodeModel> getMap() {
        Map<String, ECompanyCodeModel> map = new HashMap<String, ECompanyCodeModel>();
        for (ECompanyCodeModel direction : ECompanyCodeModel.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    ECompanyCodeModel(String code, String value) {
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
