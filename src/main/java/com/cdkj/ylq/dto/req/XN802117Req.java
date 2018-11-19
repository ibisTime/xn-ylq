package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查银行
 * @author: nyc 
 * @since: 2018年5月17日 下午3:07:11 
 * @history:
 */
public class XN802117Req {

    // 编号（选填）
    @NotBlank(message = "编号不能为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
