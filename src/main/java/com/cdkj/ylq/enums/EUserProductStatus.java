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
public enum EUserProductStatus {
    TO_APPLY("0", "可申请"), TO_CERTI("1", "认证中"), TO_APPROVE("2", "人工审核中"), APPROVE_NO(
            "3", "已驳回"), APPROVE_YES("4", "已有额度"), TO_LOAN("5", "等待放款中"), LOANING(
            "6", "生效中"), OVERDUE("7", "已逾期");

    EUserProductStatus(String code, String value) {
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
