package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN805151Req {

    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
