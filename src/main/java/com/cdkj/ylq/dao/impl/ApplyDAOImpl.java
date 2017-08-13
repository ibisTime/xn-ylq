package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IApplyDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Apply;

@Repository("applyDAOImpl")
public class ApplyDAOImpl extends AMybatisTemplate implements IApplyDAO {

    @Override
    public int insert(Apply data) {
        return super.insert(NAMESPACE.concat("insert_apply"), data);
    }

    @Override
    public int delete(Apply data) {
        return super.delete(NAMESPACE.concat("delete_apply"), data);
    }

    @Override
    public Apply select(Apply condition) {
        return super.select(NAMESPACE.concat("select_apply"), condition,
            Apply.class);
    }

    @Override
    public Long selectTotalCount(Apply condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_apply_count"),
            condition);
    }

    @Override
    public List<Apply> selectList(Apply condition) {
        return super.selectList(NAMESPACE.concat("select_apply"), condition,
            Apply.class);
    }

    @Override
    public List<Apply> selectList(Apply condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_apply"), start, count,
            condition, Apply.class);
    }

    @Override
    public int updateCancel(Apply data) {
        return super.update(NAMESPACE.concat("update_cancel"), data);
    }

    @Override
    public int updateApprove(Apply data) {
        return super.update(NAMESPACE.concat("update_approve"), data);
    }

}
