/**
 * @Title XN623202Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月14日 上午11:01:34 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 自主修改登陆密码
 * @author: taojian 
 * @since: 2019年1月14日 上午11:01:34 
 * @history:
 */
public class XN623202Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 旧密码
    @NotBlank
    private String oldPwd;

    // 新秘码
    @NotBlank
    private String newPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
