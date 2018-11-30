package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.domain.Company;

public interface ICompanyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

    public String addCompanyMan(String companyCode, String realName,
            String mobile, String loginName, String loginPwd, String remark,
            String roleCode);

    public Paginable<BusinessMan> queryCompanyManPage(int start, int limit,
            String companyCode);

}
