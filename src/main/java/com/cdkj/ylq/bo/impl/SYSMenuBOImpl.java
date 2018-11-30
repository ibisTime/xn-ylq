package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ISYSMenuBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.ISYSMenuDAO;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.enums.ECompanyCodeModel;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午7:56:45 
 * @history:
 */
@Component
public class SYSMenuBOImpl extends PaginableBOImpl<SYSMenu> implements
        ISYSMenuBO {

    @Autowired
    private ISYSMenuDAO sysMenuDAO;

    /** 
     * @see com.cdkj.ylq.bo.ISYSMenuBO#isSYSMenuExist(java.lang.String)
     */
    @Override
    public boolean isSYSMenuExist(String code) {
        SYSMenu menu = new SYSMenu();
        menu.setCode(code);
        if (sysMenuDAO.selectTotalCount(menu) == 1) {
            return true;
        }
        return false;
    }

    /** 
     * @see com.cdkj.ylq.bo.ISYSMenuBO#saveSYSMenu(com.xnjr.std.security.domain.SYSMenu)
     */
    @Override
    public int saveSYSMenu(SYSMenu data) {
        int count = 0;
        if (data != null) {
            data.setCode(OrderNoGenerater.generateM("SM"));
            data.setUpdateDatetime(new Date());
            count = sysMenuDAO.insert(data);
        }
        return count;
    }

    /** 
     * @see com.cdkj.ylq.bo.ISYSMenuBO#removeSYSMenu(java.lang.String)
     */
    @Override
    public int removeSYSMenu(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            SYSMenu data = new SYSMenu();
            data.setCode(code);
            count = sysMenuDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.cdkj.ylq.bo.ISYSMenuBO#refreshSYSMenu(com.xnjr.std.security.domain.SYSMenu)
     */
    @Override
    public int refreshSYSMenu(SYSMenu data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            data.setUpdateDatetime(new Date());
            count = sysMenuDAO.update(data);
        }
        return count;
    }

    /** 
     * @see com.cdkj.ylq.bo.ISYSMenuBO#getSYSMenu(java.lang.String)
     */
    @Override
    public SYSMenu getSYSMenu(String code) {
        SYSMenu data = null;
        if (StringUtils.isNotBlank(code)) {
            SYSMenu condition = new SYSMenu();
            condition.setCode(code);
            data = sysMenuDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.cdkj.ylq.bo.ISYSMenuBO#querySYSMenuList(com.xnjr.std.security.domain.SYSMenu)
     */
    @Override
    public List<SYSMenu> querySYSMenuList(SYSMenu data) {
        return sysMenuDAO.selectList(data);
    }

    @Override
    public List<SYSMenu> queryModelMenus() {
        SYSMenu condition = new SYSMenu();
        condition.setCompanyCode(ECompanyCodeModel.MODEL.getCode());
        List<SYSMenu> dataList = sysMenuDAO.selectList(condition);

        return dataList;
    }
}
