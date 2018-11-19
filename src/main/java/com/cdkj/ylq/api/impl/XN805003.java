package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSMenuAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.dto.req.XN805003Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 菜单-增加
 * @author: xieyj 
 * @since: 2016年5月16日 下午10:59:56 
 * @history:
 */
public class XN805003 extends AProcessor {

    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN805003Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu data = new SYSMenu();
        data.setName(req.getName());
        data.setType(req.getType());
        data.setUrl(req.getUrl());
        data.setParentCode(req.getParentCode());
        data.setOrderNo(req.getOrderNo());

        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setCompanyCode(req.getCompanyCode());
        return new PKCodeRes(sysMenuAO.addSYSMenu(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805003Req.class);
        StringValidater.validateBlank(req.getName(), req.getType(),
            req.getUrl(), req.getParentCode(), req.getOrderNo(),
            req.getUpdater(), req.getCompanyCode());
    }
}
