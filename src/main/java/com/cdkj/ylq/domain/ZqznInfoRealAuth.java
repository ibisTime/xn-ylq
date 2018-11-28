package com.cdkj.ylq.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class ZqznInfoRealAuth {

    @JSONField(name = "verify_status")
    private int verifyStatus;

    private String reason;

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ZqznInfoRealAuth{" +
                "verifyStatus=" + verifyStatus +
                ", reason='" + reason + '\'' +
                '}';
    }
}
