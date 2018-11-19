package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 注销 / 激活用户 （系统用户）
 * @author: clockorange 
 * @since: Jul 17, 2018 11:48:43 AM 
 * @history:
 */

public class XN630056Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
