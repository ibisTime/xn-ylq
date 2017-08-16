package com.cdkj.ylq.dto.req;

public class XN623147Req extends APageReq {

    private static final long serialVersionUID = 341618459877994449L;

    // 用户编号（必填）
    private String userId;

    // 状态（选填）
    private String status;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
