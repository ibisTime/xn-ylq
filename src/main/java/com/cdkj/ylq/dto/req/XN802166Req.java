package com.cdkj.ylq.dto.req;

import java.util.List;

public class XN802166Req {

    private List<String> borrowCodeList;

    private String companyCode;

    private String systemCode;

    public List<String> getBorrowCodeList() {
        return borrowCodeList;
    }

    public void setBorrowCodeList(List<String> borrowCodeList) {
        this.borrowCodeList = borrowCodeList;
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
