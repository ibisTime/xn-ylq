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
 * 渠道链接类型
 * @author: taojian 
 * @since: 2019年1月14日 下午1:24:08 
 * @history:
 */
public enum EUrlType {
    REG("1", "注册链接"), PRODUCT("0", "产品链接");

    EUrlType(String code, String value) {
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
