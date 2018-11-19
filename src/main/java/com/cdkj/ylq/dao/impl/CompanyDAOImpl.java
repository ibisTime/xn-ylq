package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.ICompanyDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Company;

@Repository("companyDAOImpl")
public class CompanyDAOImpl extends AMybatisTemplate implements ICompanyDAO {

    @Override
    public int insert(Company data) {
        return super.insert(NAMESPACE.concat("insert_company"), data);
    }

    @Override
    public int delete(Company data) {
        return 0;
    }

    @Override
    public Company select(Company condition) {
        return super.select(NAMESPACE.concat("select_company"), condition,
            Company.class);
    }

    @Override
    public Long selectTotalCount(Company condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_company_count"),
            condition);
    }

    @Override
    public List<Company> selectList(Company condition) {
        return super.selectList(NAMESPACE.concat("select_company"), condition,
            Company.class);
    }

    @Override
    public List<Company> selectList(Company condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_company"), start,
            count, condition, Company.class);
    }

    @Override
    public int update(Company data) {
        return super.update(NAMESPACE.concat("update_company"), data);
    }

    @Override
    public int updateInfo(Company data) {
        return super.update(NAMESPACE.concat("update_company_info"), data);
    }

}
