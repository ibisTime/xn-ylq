package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.ISYSRoleAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSRole;
import com.cdkj.ylq.dto.req.XN805020Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 角色-分页查询
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:23:16 
 * @history:
 */
public class XN805020 extends AProcessor {

    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN805020Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole condition = new SYSRole();
        condition.setName(req.getName());
        condition.setLevel(req.getLevel());
        condition.setUpdater(req.getUpdater());
        condition.setSystemCode(req.getSystemCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSRoleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return sysRoleAO.querySYSRolePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805020Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getSystemCode());
    }
}
