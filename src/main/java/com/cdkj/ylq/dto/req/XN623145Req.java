package com.cdkj.ylq.dto.req;

public class XN623145Req extends APageReq {

    private static final long serialVersionUID = 3592140324580851761L;

    // 用户编号（选填）
    private String userId;

    // 优惠券类型（选填）
    private String type;

    // 状态（选填）
    private String status;

    // 关联借款编号（选填）
    private String borrowCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

}
