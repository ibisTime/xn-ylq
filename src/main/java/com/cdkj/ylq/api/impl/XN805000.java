package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.ISYSMenuAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.dto.req.XN805000Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 菜单-分页查询
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:38:19 
 * @history:
 */
public class XN805000 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN805000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu condition = new SYSMenu();
        condition.setNameForQuery(req.getName());
        condition.setType(req.getType());
        condition.setParentCode(req.getParentCode());
        condition.setUpdater(req.getUpdater());
        condition.setSystemCode(req.getSystemCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSMenuAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return sysMenuAO.querySYSMenuPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805000Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getSystemCode());
    }
}
