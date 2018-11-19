package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630062Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 审核用户（平台方）
 * @author: jiafr 
 * @since: 2018年9月28日 下午8:39:09 
 * @history:
 */
public class XN630062 extends AProcessor {
    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN630062Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysUserAO.approveSYSUser(req.getUserId(), req.getApproveResult(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630062Req.class);
        ObjValidater.validateReq(req);
    }
}
