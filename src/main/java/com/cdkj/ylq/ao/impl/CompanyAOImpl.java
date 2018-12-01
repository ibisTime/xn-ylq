package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.ICompanyAO;
import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.ISYSRoleBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.dto.req.XN630100Req;
import com.cdkj.ylq.enums.EBoolean;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private IBusinessManBO businessManBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private ISYSRoleBO sysRoleBO;

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
    public String addCompanyMan(String companyCode, String realName,
            String mobile, String loginName, String loginPwd, String remark,
            String roleCode) {
        XN630100Req req = new XN630100Req();
        req.setLoginName(loginName);
        req.setLoginPwd(loginPwd);
        req.setMobile(mobile);
        req.setRealName(realName);
        req.setRemark(remark);
        req.setRoleCode(roleCode);
        req.setIsAdmin(EBoolean.NO.getCode());
        return businessManBO.saveBusinessMan(req);
    }

    @Override
    public Paginable<BusinessMan> queryCompanyManPage(int start, int limit,
            String companyCode) {
        BusinessMan condition = new BusinessMan();
        condition.setCompanyCode(companyCode);
        return businessManBO.getPaginable(start, limit, condition);
    }

}
