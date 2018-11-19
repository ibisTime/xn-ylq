package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSMenuRoleAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSMenuRole;
import com.cdkj.ylq.dto.req.XN805026Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 菜单角色-查询菜单列表
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:26:30 
 * @history:
 */
public class XN805026 extends AProcessor {
    private ISYSMenuRoleAO sysMenuRoleAO = SpringContextHolder
        .getBean(ISYSMenuRoleAO.class);

    private XN805026Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenuRole data = new SYSMenuRole();
        data.setRoleCode(req.getRoleCode());
        data.setParentCode(req.getParentCode());
        data.setType(req.getType());
        data.setCompanyCode(req.getCompanyCode());
        return sysMenuRoleAO.querySYSMenuList(data);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805026Req.class);
        StringValidater.validateBlank(req.getRoleCode(), req.getCompanyCode());
    }
}
