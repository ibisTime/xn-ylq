/**
 * @Title XN623201Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月14日 上午10:59:50 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 渠道商登陆
 * @author: taojian 
 * @since: 2019年1月14日 上午10:59:50 
 * @history:
 */
public class XN623201Req {

    // 登录名
    @NotBlank
    private String loginName;

    // 登陆密码
    @NotBlank
    private String loginPwd;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
