package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630051Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 普通登录
 * @author: xieyj 
 * @since: 2016年11月22日 下午3:39:17 
 * @history:
 */
public class XN630051 extends AProcessor {
    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630051Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = userAO.doLogin(req.getLoginName(), req.getLoginPwd());
        return new PKCodeRes(userId);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630051Req.class);
        ObjValidater.validateReq(req);
    }
}
