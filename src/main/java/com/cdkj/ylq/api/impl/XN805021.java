package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSRoleAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSRole;
import com.cdkj.ylq.dto.req.XN805021Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 角色-列表查询
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:24:46 
 * @history:
 */
public class XN805021 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN805021Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole condition = new SYSRole();
        condition.setName(req.getName());
        condition.setLevel(req.getLevel());
        condition.setCompanyCode(req.getCompanyCode());
        return sysRoleAO.querySYSRoleList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805021Req.class);
        StringValidater.validateBlank(req.getCompanyCode());
    }
}