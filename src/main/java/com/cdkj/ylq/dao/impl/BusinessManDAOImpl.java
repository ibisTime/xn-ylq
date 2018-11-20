package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IBusinessManDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.BusinessMan;

//CHECK 。。。 
@Repository("businessManDAOImpl")
public class BusinessManDAOImpl extends AMybatisTemplate implements
        IBusinessManDAO {

    @Override
    public int insert(BusinessMan data) {
        return super.insert(NAMESPACE.concat("insert_businessMan"), data);
    }

    @Override
    public int delete(BusinessMan data) {
        return super.delete(NAMESPACE.concat("delete_businessMan"), data);
    }

    @Override
    public BusinessMan select(BusinessMan condition) {
        return super.select(NAMESPACE.concat("select_businessMan"), condition,
            BusinessMan.class);
    }

    @Override
    public Long selectTotalCount(BusinessMan condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_businessMan_count"), condition);
    }

    @Override
    public List<BusinessMan> selectList(BusinessMan condition) {
        return super.selectList(NAMESPACE.concat("select_businessMan"),
            condition, BusinessMan.class);
    }

    @Override
    public List<BusinessMan> selectList(BusinessMan condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_businessMan"), start,
            count, condition, BusinessMan.class);
    }

    @Override
    public int updateMobile(BusinessMan data) {
        return super.update(NAMESPACE.concat("update_mobile"), data);
    }

    @Override
    public int updatePwd(BusinessMan data) {
        return super.update(NAMESPACE.concat("update_login_pwd"), data);
    }

    @Override
    public int updateStatus(BusinessMan data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateRole(BusinessMan data) {
        return super.update(NAMESPACE.concat("update_role"), data);
    }

    @Override
    public int updatePhoto(BusinessMan data) {
        return super.update(NAMESPACE.concat("update_photo"), data);
    }

    @Override
    public int updateCompany(BusinessMan data) {
        return super.update(NAMESPACE.concat("update_company"), data);
    }

}
