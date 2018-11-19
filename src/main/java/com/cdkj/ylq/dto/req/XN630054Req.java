/**
 * @Title XN630054Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月23日 下午3:24:40 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * 根据旧密码修改密码
 * @author: dl 
 * @since: 2018年8月23日 下午3:24:40 
 * @history:
 */
public class XN630054Req {

    private String newLoginPwd;

    private String oldLoginPwd;

    private String userId;

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

    public String getOldLoginPwd() {
        return oldLoginPwd;
    }

    public void setOldLoginPwd(String oldLoginPwd) {
        this.oldLoginPwd = oldLoginPwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
