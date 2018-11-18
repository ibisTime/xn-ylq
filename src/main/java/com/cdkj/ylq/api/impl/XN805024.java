package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSRoleAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805024Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 角色-删除
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:51 
 * @history:
 */
public class XN805024 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN805024Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new BooleanRes(sysRoleAO.dropSYSRole(req.getCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805024Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
