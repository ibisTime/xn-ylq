/**
 * @Title XN630307Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午7:08:17 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 详情查询公司
 * @author: tao 
 * @since: 2018年8月17日 下午7:08:17 
 * @history:
 */
public class XN630307Req {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // 公司编号
    @NotBlank
    private String code;
}
