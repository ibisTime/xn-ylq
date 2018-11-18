package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ISYSRoleBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.ISYSRoleDAO;
import com.cdkj.ylq.domain.SYSRole;

@Component
public class SYSRoleBOImpl extends PaginableBOImpl<SYSRole> implements
        ISYSRoleBO {

    @Autowired
    private ISYSRoleDAO sysRoleDAO;

    @Override
    public boolean isSYSRoleExist(String code) {
        SYSRole role = new SYSRole();
        role.setCode(code);
        if (sysRoleDAO.selectTotalCount(role) >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public int saveSYSRole(SYSRole data) {
        int count = 0;
        if (data != null) {
            data.setCode(OrderNoGenerater.generateM("SR"));
            data.setUpdateDatetime(new Date());
            count = sysRoleDAO.insert(data);
        }
        return count;
    }

    @Override
    public int removeSYSRole(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            SYSRole data = new SYSRole();
            data.setCode(code);
            count = sysRoleDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshSYSRole(SYSRole data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            data.setUpdateDatetime(new Date());
            count = sysRoleDAO.update(data);
        }
        return count;
    }

    @Override
    public List<SYSRole> querySYSRoleList(SYSRole data) {
        return sysRoleDAO.selectList(data);
    }

    @Override
    public SYSRole getSYSRole(String code) {
        SYSRole data = null;
        if (StringUtils.isNotBlank(code)) {
            SYSRole condition = new SYSRole();
            condition.setCode(code);
            data = sysRoleDAO.select(condition);
        }
        return data;
    }
}
