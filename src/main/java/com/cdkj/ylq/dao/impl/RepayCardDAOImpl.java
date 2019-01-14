package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IRepayCardDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.RepayCard;

//CHECK 。。。 
@Repository("repayCardDAOImpl")
public class RepayCardDAOImpl extends AMybatisTemplate implements IRepayCardDAO {

    @Override
    public int insert(RepayCard data) {
        return super.insert(NAMESPACE.concat("insert_repayCard"), data);
    }

    @Override
    public int delete(RepayCard data) {
        return super.delete(NAMESPACE.concat("delete_repayCard"), data);
    }

    @Override
    public RepayCard select(RepayCard condition) {
        return super.select(NAMESPACE.concat("select_repayCard"), condition,
            RepayCard.class);
    }

    @Override
    public Long selectTotalCount(RepayCard condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_repayCard_count"), condition);
    }

    @Override
    public List<RepayCard> selectList(RepayCard condition) {
        return super.selectList(NAMESPACE.concat("select_repayCard"),
            condition, RepayCard.class);
    }

    @Override
    public List<RepayCard> selectList(RepayCard condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_repayCard"), start,
            count, condition, RepayCard.class);
    }

    @Override
    public int updateAmount(RepayCard data) {
        return super.update(NAMESPACE.concat("update_amount"), data);
    }

    @Override
    public int update(RepayCard data) {
        return super.update(NAMESPACE.concat("update_repayCard"), data);
    }

}
