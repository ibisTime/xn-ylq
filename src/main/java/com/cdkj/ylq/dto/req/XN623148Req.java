package com.cdkj.ylq.dto.req;

public class XN623148Req extends APageReq {

    private static final long serialVersionUID = 8379064020980760674L;

    // 用户编号（必填）
    private String userId;

    // 产品编号（必填）
    private String productCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
