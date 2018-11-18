package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.ISYSMenuRoleDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.domain.SYSMenuRole;

@Repository("sysMenuRoleDAOImpl")
public class SYSMenuRoleDAOImpl extends AMybatisTemplate implements
        ISYSMenuRoleDAO {

    @Override
    public int insert(SYSMenuRole data) {
        return super.insert(NAMESPACE.concat("insert_sysMenuRole"), data);
    }

    @Override
    public int delete(SYSMenuRole data) {
        return super.delete(NAMESPACE.concat("delete_sysMenuRole"), data);
    }

    @Override
    public SYSMenuRole select(SYSMenuRole condition) {
        return super.select("select_sysMenuRole", condition, SYSMenuRole.class);
    }

    @Override
    public Long selectTotalCount(SYSMenuRole condition) {
        return super.selectTotalCount("select_sysMenuRole_count", condition);
    }

    @Override
    public List<SYSMenuRole> selectList(SYSMenuRole condition) {
        return super.selectList("select_sysMenuRole", condition,
            SYSMenuRole.class);
    }

    @Override
    public List<SYSMenuRole> selectList(SYSMenuRole condition, int start,
            int count) {
        return super.selectList("select_sysMenuRole", start, count, condition,
            SYSMenuRole.class);
    }

    @Override
    public List<SYSMenu> selectSYSMenuList(SYSMenuRole data) {
        return super.selectList(NAMESPACE.concat("select_sysMenu_list"), data,
            SYSMenu.class);
    }

    @Override
    public int deleteSYSMenuList(SYSMenuRole data) {
        return super.delete("delete_sysMenuRoleList", data);
    }
}
