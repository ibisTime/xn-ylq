package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN802321Req {

    // 编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
