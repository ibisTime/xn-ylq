package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSMenuAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805002Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 菜单-详情
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:39:48 
 * @history:
 */
public class XN805002 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN805002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysMenuAO.getSYSMenu(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805002Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
