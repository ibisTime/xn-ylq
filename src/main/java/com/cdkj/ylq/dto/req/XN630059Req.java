package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630059Req {

    // 用户编号
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // photo
    @NotBlank(message = "用户photo不能为空")
    private String photo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
