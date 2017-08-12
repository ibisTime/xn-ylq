/**
 * @Title ECheckResult.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午2:58:54 
 * @version V1.0   
 */
package com.cdkj.ylq.enums;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午2:58:54 
 * @history:
 */
public enum EProductLevel {
    ONE("1", "LV.1"), TWO("2", "LV.2"), THREE("3", "LV.3"), FOUR("4", "LV.4")

    , ONE_COLOR("#0cb8ae", "LV.1颜色"), TWO_COLOR("#fba72e", "LV.2颜色"), THREE_COLOR(
            "#f16254", "LV.3颜色"), FOUR_COLOR("#28a6e6", "LV.4颜色");

    EProductLevel(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getLevelCode(String level) {
        String color = "";
        if (level.equals(EProductLevel.ONE.getCode())) {
            color = EProductLevel.ONE_COLOR.getCode();
        } else if (level.equals(EProductLevel.TWO.getCode())) {
            color = EProductLevel.TWO_COLOR.getCode();
        } else if (level.equals(EProductLevel.THREE.getCode())) {
            color = EProductLevel.THREE_COLOR.getCode();
        } else if (level.equals(EProductLevel.FOUR.getCode())) {
            color = EProductLevel.FOUR_COLOR.getCode();
        }
        return color;
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
