package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 重置手机 （系统用户）
 * @author: clockorange 
 * @since: Jul 17, 2018 11:56:26 AM 
 * @history:
 */
public class XN630052Req {

    // （必填）用户编号
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // （必填）新手机号
    @NotBlank(message = "新手机号不能为空")
    private String newMobile;

    // (必填) 新手机号验证码
    @NotBlank(message = "新手机号验证码不能为空")
    private String smsCaptcha;

    // 备注
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
