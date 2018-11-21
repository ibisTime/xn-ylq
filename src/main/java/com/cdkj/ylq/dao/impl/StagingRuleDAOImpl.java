package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IStagingRuleDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.StagingRule;

//CHECK 。。。 
@Repository("stagingRuleDAOImpl")
public class StagingRuleDAOImpl extends AMybatisTemplate implements
        IStagingRuleDAO {

    @Override
    public int insert(StagingRule data) {
        return super.insert(NAMESPACE.concat("insert_stagingRule"), data);
    }

    @Override
    public int delete(StagingRule data) {
        return super.delete(NAMESPACE.concat("delete_stagingRule"), data);
    }

    @Override
    public StagingRule select(StagingRule condition) {
        return super.select(NAMESPACE.concat("select_stagingRule"), condition,
            StagingRule.class);
    }

    @Override
    public Long selectTotalCount(StagingRule condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_stagingRule_count"), condition);
    }

    @Override
    public List<StagingRule> selectList(StagingRule condition) {
        return super.selectList(NAMESPACE.concat("select_stagingRule"),
            condition, StagingRule.class);
    }

    @Override
    public List<StagingRule> selectList(StagingRule condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_stagingRule"), start,
            count, condition, StagingRule.class);
    }

    @Override
    public int update(StagingRule data) {
        return super.update(NAMESPACE.concat("update_stagingRule"), data);
    }

}
