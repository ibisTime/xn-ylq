package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.ICompanyDAO;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.exception.BizException;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company> implements
        ICompanyBO {

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IBusinessManBO businessManBO;

    @Override
    @Transactional
    public String saveCompany(String userId) {
        String code = null;
        if (StringUtils.isNotBlank(userId)) {
            Company data = new Company();
            code = OrderNoGenerater.generateM(EGeneratePrefix.GS.getCode());
            data.setCode(code);
            data.setUserId(userId);
            data.setCreateDatetime(new Date());
            companyDAO.insert(data);

            // 更新用户公司
            businessManBO.refereshCompany(userId, code);
        }
        return code;
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyDAO.selectList(condition);
    }

    @Override
    public Company getCompany(String code) {
        Company data = null;
        if (StringUtils.isNotBlank(code)) {
            Company condition = new Company();
            condition.setCode(code);
            data = companyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "公司不存在");
            }
            data.setBusinessMan(businessManBO.getBusinessManByCompanyCode(data
                .getCode()));
        }
        return data;
    }

}
