package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630050Req;
import com.cdkj.ylq.dto.res.XN627312Res;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 新增用户
 * @author: nyc 
 * @since: 2018年4月26日 下午6:14:01 
 * @history:
 */

public class XN630050 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN627312Res(userAO.addSYSUser(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630050Req.class);
        ObjValidater.validateReq(req);
    }
}
