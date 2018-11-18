package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.domain.SYSMenuRole;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午10:37:44 
 * @history:
 */
public interface ISYSMenuRoleAO {

    public int addSYSMenuRole(SYSMenuRole data);

    public List<SYSMenu> querySYSMenuList(SYSMenuRole data);

}
