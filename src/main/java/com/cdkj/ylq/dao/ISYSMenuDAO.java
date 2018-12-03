package com.cdkj.ylq.dao;

import java.util.List;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.SYSMenu;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午6:54:55 
 * @history:
 */
public interface ISYSMenuDAO extends IBaseDAO<SYSMenu> {
    String NAMESPACE = ISYSMenuDAO.class.getName().concat(".");

    public List<SYSMenu> selectMenuList(SYSMenu condition);

    public int update(SYSMenu data);
}
