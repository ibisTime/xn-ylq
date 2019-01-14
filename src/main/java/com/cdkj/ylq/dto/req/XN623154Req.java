/**
 * @Title XN623154Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月14日 下午2:43:40 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 注册链接数减1
 * @author: taojian 
 * @since: 2019年1月14日 下午2:43:40 
 * @history:
 */
public class XN623154Req {

    // 链接编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
