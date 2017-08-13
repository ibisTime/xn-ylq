/**
 * @Title XN798015Res.java 
 * @Package com.std.certi.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年7月26日 下午5:55:24 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年7月26日 下午5:55:24 
 * @history:
 */
public class XN798015Res extends XN798017Res {

    // 是否已授权
    private boolean authorized;

    // 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
    private String bizNo;

    // 用户的芝麻信用评分。分值范围[350,950]。如果用户数据不足，无法评分时，返回字符串"N/A"。
    private String zmScore;

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getZmScore() {
        return zmScore;
    }

    public void setZmScore(String zmScore) {
        this.zmScore = zmScore;
    }
}
