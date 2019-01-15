package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IWayerDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Wayer;

@Repository("wayerDAOImpl")
public class WayerDAOImpl extends AMybatisTemplate implements IWayerDAO {

    @Override
    public int insert(Wayer data) {
        return super.insert(NAMESPACE.concat("insert_wayer"), data);
    }

    @Override
    public int delete(Wayer data) {
        return super.delete(NAMESPACE.concat("delete_wayer"), data);
    }

    @Override
    public Wayer select(Wayer condition) {
        return super.select(NAMESPACE.concat("select_wayer"), condition,
            Wayer.class);
    }

    @Override
    public Long selectTotalCount(Wayer condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_wayer_count"),
            condition);
    }

    @Override
    public List<Wayer> selectList(Wayer condition) {
        return super.selectList(NAMESPACE.concat("select_wayer"), condition,
            Wayer.class);
    }

    @Override
    public List<Wayer> selectList(Wayer condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_wayer"), start, count,
            condition, Wayer.class);
    }

    @Override
    public int updatePwd(Wayer data) {
        return super.update(NAMESPACE.concat("update_pwd"), data);
    }

    @Override
    public int updateStatus(Wayer data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateUrlCount(Wayer data) {
        return super.update(NAMESPACE.concat("update_url_count"), data);
    }

    @Override
    public int updateUserCount(Wayer data) {
        return super.update(NAMESPACE.concat("update_user_count"), data);
    }

}
