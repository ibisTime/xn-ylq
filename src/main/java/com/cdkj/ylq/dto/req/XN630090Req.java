package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发送短信验证码
 * @author: xieyj 
 * @since: 2017年2月13日 下午1:47:40 
 * @history:
 */
public class XN630090Req {

    // 接受短信的手机号--必填
    @NotBlank
    private String mobile;

    // 业务类型--必填
    @NotBlank
    private String bizType;

    @NotBlank
    private String companyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

}
