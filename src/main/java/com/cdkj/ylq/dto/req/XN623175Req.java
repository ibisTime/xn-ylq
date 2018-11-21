/**
 * @Title XN623170Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月21日 上午11:14:39 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月21日 上午11:14:39 
 * @history:
 */
public class XN623175Req extends APageReq {

    private static final long serialVersionUID = 9140079820734513474L;

    @NotBlank
    private String companyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
