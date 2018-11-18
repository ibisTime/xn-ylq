package com.cdkj.ylq.dao;

import java.util.List;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.domain.SYSMenuRole;

/**
 * 角色菜单
 * @author: Gejin 
 * @since: 2016年4月16日 下午10:24:53 
 * @history:
 */
public interface ISYSMenuRoleDAO extends IBaseDAO<SYSMenuRole> {
    String NAMESPACE = ISYSMenuRoleDAO.class.getName().concat(".");

    public List<SYSMenu> selectSYSMenuList(SYSMenuRole data);

    public int deleteSYSMenuList(SYSMenuRole data);
}
