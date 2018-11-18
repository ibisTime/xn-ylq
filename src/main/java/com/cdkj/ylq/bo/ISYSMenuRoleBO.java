/**
 * @Title ISYSMenuRoleBO.java 
 * @Package com.std.security.bo 
 * @Description 
 * @author xieyj  
 * @date 2016年4月17日 上午8:57:11 
 * @version V1.0   
 */
package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.domain.SYSMenuRole;

/** 
 * 菜单角色
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:57:11 
 * @history:
 */
public interface ISYSMenuRoleBO extends IPaginableBO<SYSMenuRole> {
    public int saveSYSMenuRole(SYSMenuRole data);

    public int removeSYSMenuList(String roleCode);

    public List<SYSMenu> querySYSMenuList(SYSMenuRole data);
}
