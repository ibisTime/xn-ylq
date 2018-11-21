package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSRoleAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSRole;
import com.cdkj.ylq.dto.req.XN805023Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 角色-新增
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:15 
 * @history:
 */
public class XN805023 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN805023Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole data = new SYSRole();
        data.setName(req.getName());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setCompanyCode(req.getCompanyCode());
        return new PKCodeRes(sysRoleAO.addSYSRole(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805023Req.class);
        StringValidater.validateBlank(req.getName(), req.getUpdater(),
            req.getCompanyCode());
    }

}
