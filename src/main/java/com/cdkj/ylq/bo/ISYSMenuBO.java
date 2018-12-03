package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.SYSMenu;

/**
 * 
 * @author: Gejin 
 * @since: 2016年4月16日 下午7:48:35 
 * @history:
 */
public interface ISYSMenuBO extends IPaginableBO<SYSMenu> {
    public boolean isSYSMenuExist(String code);

    public int saveSYSMenu(SYSMenu data);

    public int removeSYSMenu(String code);

    public int refreshSYSMenu(SYSMenu data);

    public SYSMenu getSYSMenu(String code);

    public List<SYSMenu> querySYSMenuList(SYSMenu data);

    public List<SYSMenu> queryModelMenus();

    public SYSMenu getRootMenu(String companyCode);

    public List<SYSMenu> queryMenuList(String menuCode);
}
