package com.cdkj.ylq.dto.req;

import java.util.List;

import com.cdkj.ylq.domain.BaofooPay;

public class XN802165Req {

    // 代付信息（必填）
    private List<BaofooPay> payInfoList;

    // 回调地址（必填）
    private String backUrl;

    // 公司编号（必填）
    private String companyCode;

    // 系统编号（必填）
    private String systemCode;

    public List<BaofooPay> getPayInfoList() {
        return payInfoList;
    }

    public void setPayInfoList(List<BaofooPay> payInfoList) {
        this.payInfoList = payInfoList;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
