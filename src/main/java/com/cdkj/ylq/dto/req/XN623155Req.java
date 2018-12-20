/**
 * @Title XN623150Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午3:43:12 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月20日 下午3:43:12 
 * @history:
 */
public class XN623155Req extends APageReq {

    private static final long serialVersionUID = 537463847167988484L;

    // 姓名
    private String name;

    // 状态
    private String status;

    // 公司编号
    @NotBlank
    private String companyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
