/**
 * @Title XN630118Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年12月3日 下午3:56:16 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年12月3日 下午3:56:16 
 * @history:
 */
public class XN630118Req {

    @NotBlank
    private String companyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
