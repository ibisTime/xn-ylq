package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.INoticerDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Noticer;

//CHECK 。。。 
@Repository("noticerDAOImpl")
public class NoticerDAOImpl extends AMybatisTemplate implements INoticerDAO {

    @Override
    public int insert(Noticer data) {
        return super.insert(NAMESPACE.concat("insert_noticer"), data);
    }

    @Override
    public int delete(Noticer data) {
        return super.delete(NAMESPACE.concat("delete_noticer"), data);
    }

    @Override
    public Noticer select(Noticer condition) {
        return super.select(NAMESPACE.concat("select_noticer"), condition,
            Noticer.class);
    }

    @Override
    public Long selectTotalCount(Noticer condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_noticer_count"),
            condition);
    }

    @Override
    public List<Noticer> selectList(Noticer condition) {
        return super.selectList(NAMESPACE.concat("select_noticer"), condition,
            Noticer.class);
    }

    @Override
    public List<Noticer> selectList(Noticer condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_noticer"), start,
            count, condition, Noticer.class);
    }

    @Override
    public int update(Noticer data) {
        return super.update(NAMESPACE.concat("update_noticer"), data);
    }

}
