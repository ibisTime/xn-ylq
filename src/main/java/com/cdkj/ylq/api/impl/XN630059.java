package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630059Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 修改照片
 * @author: clockorange 
 * @since: Aug 6, 2018 2:26:53 PM 
 * @history:
 */

public class XN630059 extends AProcessor {
    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN630059Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysUserAO.doModifyPhoto(req.getUserId(), req.getPhoto());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630059Req.class);
        ObjValidater.validateReq(req);
    }

}
