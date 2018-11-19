package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630057Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 设置角色
 * @author: nyc 
 * @since: 2018年4月26日 下午6:14:01 
 * @history:
 */
public class XN630057 extends AProcessor {
    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630057Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doRoleSYSUser(req.getUserId(), req.getRoleCode(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630057Req.class);
        ObjValidater.validateReq(req);
    }

}
