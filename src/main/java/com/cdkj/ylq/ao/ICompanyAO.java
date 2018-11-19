package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Company;

public interface ICompanyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

    public Company getCompanyByUser(String userId);

    public void completeCompanyOwner(String userId, String bussinessLicense,
            String certificateTemplate, String contractTemplate);

}
