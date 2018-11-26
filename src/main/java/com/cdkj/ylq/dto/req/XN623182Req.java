/**
 * @Title XN623182Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月26日 下午10:26:23 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月26日 下午10:26:23 
 * @history:
 */
public class XN623182Req {

    // 分期编号
    @NotBlank
    private String stagingCode;

    public String getStagingCode() {
        return stagingCode;
    }

    public void setStagingCode(String stagingCode) {
        this.stagingCode = stagingCode;
    }

}
