package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.ICompanyAO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.enums.ESYSUserKind;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public void completeCompanyOwner(String userId, String bussinessLicense,
            String certificateTemplate, String contractTemplate) {
        SYSUser sysUser = sysUserBO.getSYSUser(userId);
        if (!ESYSUserKind.OWNER.getCode().equals(sysUser.getKind())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "不是产权端用户，不能操作");
        }

        Company company = companyBO.getCompanyByUserId(sysUser.getUserId());
        companyBO.refreshCompanyInfo(company, bussinessLicense,
            certificateTemplate, contractTemplate);
    }

    @Override
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition) {
        return companyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyBO.queryCompanyList(condition);
    }

    @Override
    public Company getCompany(String code) {
        return companyBO.getCompany(code);
    }

    @Override
    public Company getCompanyByUser(String userId) {
        return companyBO.getCompanyByUserId(userId);
    }

}
