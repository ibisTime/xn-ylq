/**
 * @Title EDictType.java 
 * @Package com.std.security.enums 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:15:02 
 * @version V1.0   
 */
package com.cdkj.ylq.enums;


/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午5:15:02 
 * @history:
 */
public enum EUserRefereeType {
    C("C", "c端用户"), W("W", "渠道");

    EUserRefereeType(String code, String value) {
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
