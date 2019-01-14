/**
 * @Title XN623205Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author taojian  
 * @date 2019年1月14日 上午11:10:43 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * 分页查渠道商
 * @author: taojian 
 * @since: 2019年1月14日 上午11:10:43 
 * @history:
 */
public class XN623205Req extends APageReq {

    private static final long serialVersionUID = -7857976707532975252L;

    // 公司编号
    private String companyCode;

    // 状态
    private String status;

    // 名字
    private String name;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
