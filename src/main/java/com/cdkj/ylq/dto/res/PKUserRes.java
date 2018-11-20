package com.cdkj.ylq.dto.res;

public class PKUserRes {

    // 编号
    private String userId;

    public PKUserRes() {
    }

    public PKUserRes(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
