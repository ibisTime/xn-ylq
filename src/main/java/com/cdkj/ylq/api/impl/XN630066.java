package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.dto.req.XN630066Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 分页查询用户
 * @author: chenshan 
 * @since: 2018年3月26日 下午6:28:36 
 * @history:
 */
public class XN630066 extends AProcessor {

    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN630066Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSUser condition = new SYSUser();
        condition.setRealName(req.getRealName());
        condition.setRoleCode(req.getRoleCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISYSUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return sysUserAO.querySYSUserList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630066Req.class);
    }

}
