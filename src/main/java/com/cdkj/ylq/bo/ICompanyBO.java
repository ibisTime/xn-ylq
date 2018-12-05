package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Company;

public interface ICompanyBO extends IPaginableBO<Company> {

    public String saveCompany(String userId, String code, String name,
            String logo);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

}
