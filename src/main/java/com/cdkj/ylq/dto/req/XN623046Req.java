/**
 * @Title XN623041Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午8:31:49 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午8:31:49 
 * @history:
 */
public class XN623046Req {

    // userId(必填)
    private String userId;

    // 芝麻信用唯一认证标识（必填）
    private String bizNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

}
