/**
 * @Title XN623203Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月14日 上午11:03:50 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 注销/恢复
 * @author: taojian 
 * @since: 2019年1月14日 上午11:03:50 
 * @history:
 */
public class XN623203Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 更新人
    @NotBlank
    private String updater;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
