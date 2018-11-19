package com.cdkj.ylq.enums;

/**
 * 
 * @author: jiafr 
 * @since: 2018年9月30日 下午1:35:24 
 * @history:
 */
public enum ERoleCode {

    OWNER("JS201809301134241553541", "产权端"), MAINTAIN(
            "JS201809301134504008291", "养护端"), AGENT("JS201810041749178264163",
            "代理商"), SALEMANS("JS201810041749297484833", "业务员"), BUSINESS(
            "JS201811081749297484833", "商家");

    ERoleCode(String code, String value) {
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
