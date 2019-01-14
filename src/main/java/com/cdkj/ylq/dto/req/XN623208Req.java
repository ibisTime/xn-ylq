/**
 * @Title XN623208Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月14日 上午11:13:14 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 列表查渠道商所属用户
 * @author: taojian 
 * @since: 2019年1月14日 上午11:13:14 
 * @history:
 */
public class XN623208Req {

    // 用户编号
    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
