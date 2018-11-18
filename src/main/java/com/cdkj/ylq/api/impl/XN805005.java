package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSMenuAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.dto.req.XN805005Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 菜单-修改
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:46:32 
 * @history:
 */
public class XN805005 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN805005Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu condition = new SYSMenu();
        condition.setCode(req.getCode());
        condition.setName(req.getName());
        condition.setType(req.getType());
        condition.setUrl(req.getUrl());
        condition.setParentCode(req.getParentCode());
        condition.setOrderNo(req.getOrderNo());

        condition.setUpdater(req.getUpdater());
        condition.setRemark(req.getRemark());
        return new BooleanRes(sysMenuAO.editSYSMenu(condition));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805005Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getType(), req.getUrl(), req.getParentCode(), req.getOrderNo(),
            req.getUpdater());
    }
}
