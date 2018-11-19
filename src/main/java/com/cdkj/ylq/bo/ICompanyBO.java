package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.dto.req.XN630061Req;
import com.cdkj.ylq.dto.req.XN630063Req;
import com.cdkj.ylq.dto.req.XN730072Req;
import com.cdkj.ylq.dto.req.XN730073Req;

public interface ICompanyBO extends IPaginableBO<Company> {

    public String saveCompany(XN730072Req req, String userId);

    public String saveCompany(XN630063Req req, String userId);

    public String saveCompany(String userId);

    public void refreshCompany(XN630061Req req);

    public void refreshCompany(XN730073Req req);

    public int refreshCompanyInfo(Company company, String bussinessLicense,
            String certificateTemplate, String contractTemplate);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

    public Company getCompanyByUserId(String userId);

}
