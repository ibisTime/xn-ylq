/**
 * @Title XN630038Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月30日 下午5:25:03 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月30日 下午5:25:03 
 * @history:
 */
public class XN630308Req extends APageReq {

    private static final long serialVersionUID = 6614441642540582226L;

    @NotBlank
    private String companyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
