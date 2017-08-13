/**
 * @Title InfoZMCredit.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月13日 下午2:37:46 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.domain.ZmWatchListDetail;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月13日 下午2:37:46 
 * @history:
 */
public class InfoZMCredit {
    // 是否已授权
    private boolean authorized;

    // 用户的芝麻信用评分。分值范围[350,950]。如果用户数据不足，无法评分时，返回字符串"N/A"。
    private String zmScore;

    // true=命中 在关注名单中 false=未命中
    private boolean isMatched;

    // 行业关注名单信息列表
    List<ZmWatchListDetail> details;

    private String appId;

    private String param;

    private String signature;

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getZmScore() {
        return zmScore;
    }

    public void setZmScore(String zmScore) {
        this.zmScore = zmScore;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean isMatched) {
        this.isMatched = isMatched;
    }

    public List<ZmWatchListDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ZmWatchListDetail> details) {
        this.details = details;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
