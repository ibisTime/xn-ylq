package com.cdkj.ylq.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class ZqznInfoBack {

    @JSONField(name = "issued_by")
    private String issuedBy;

    @JSONField(name = "valid_date")
    private String validDate;

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    @Override
    public String toString() {
        return "ZqznInfoBack{" +
                "issuedBy='" + issuedBy + '\'' +
                ", validDate='" + validDate + '\'' +
                '}';
    }
}
