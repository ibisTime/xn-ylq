package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSMenuAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.dto.req.XN805001Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 菜单-列表查询
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:38:06 
 * @history:
 */
public class XN805001 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN805001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu condition = new SYSMenu();
        condition.setNameForQuery(req.getName());
        condition.setType(req.getType());
        condition.setParentCode(req.getParentCode());
        condition.setCompanyCode(req.getCompanyCode());
        return sysMenuAO.querySYSMenuList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805001Req.class);
        StringValidater.validateBlank(req.getCompanyCode());
    }
}
