package com.cdkj.ylq.dto.res;

public class XN805041Res {
    private String userId;

    private String userReferee;

    private Long amount;

    public XN805041Res() {
    }

    public XN805041Res(String userId, String userReferee, Long amount) {
        this.userId = userId;
        this.userReferee = userReferee;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
