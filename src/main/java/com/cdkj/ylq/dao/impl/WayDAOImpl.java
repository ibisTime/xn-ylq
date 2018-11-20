package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IWayDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Way;

//CHECK 。。。 
@Repository("wayDAOImpl")
public class WayDAOImpl extends AMybatisTemplate implements IWayDAO {

    @Override
    public int insert(Way data) {
        return super.insert(NAMESPACE.concat("insert_way"), data);
    }

    @Override
    public int delete(Way data) {
        return super.delete(NAMESPACE.concat("delete_way"), data);
    }

    @Override
    public Way select(Way condition) {
        return super.select(NAMESPACE.concat("select_way"), condition,
            Way.class);
    }

    @Override
    public Long selectTotalCount(Way condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_way_count"),
            condition);
    }

    @Override
    public List<Way> selectList(Way condition) {
        return super.selectList(NAMESPACE.concat("select_way"), condition,
            Way.class);
    }

    @Override
    public List<Way> selectList(Way condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_way"), start, count,
            condition, Way.class);
    }

    @Override
    public int updateWay(Way data) {
        return super.update(NAMESPACE.concat("updater_way"), data);
    }

    @Override
    public int updatePointCount(Way data) {
        return super.update(NAMESPACE.concat("updater_point_count"), data);
    }

    @Override
    public int updateUserCount(Way data) {
        return super.update(NAMESPACE.concat("updater_user_count"), data);
    }

}
