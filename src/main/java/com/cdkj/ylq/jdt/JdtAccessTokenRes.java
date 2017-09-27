/**
 * @Title AccessTokenRes.java 
 * @Package com.cdkj.ylq.jdt 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月27日 下午4:10:40 
 * @version V1.0   
 */
package com.cdkj.ylq.jdt;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月27日 下午4:10:40 
 * @history:
 */
public class JdtAccessTokenRes {

    // SUCCESS - 获取token成功 FAILED - 失败
    private String result;

    // 获得的access token值
    private String accessToken;

    // 获取token失败，云端返回的信息
    private String info;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
