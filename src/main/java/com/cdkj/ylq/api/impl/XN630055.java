package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630055Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 管理员重置登录密码
 * @author: chenshan 
 * @since: 2018年3月25日 下午4:26:45 
 * @history:
 */
public class XN630055 extends AProcessor {
    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630055Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.resetAdminLoginPwd(req.getUserId(), req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630055Req.class);
        ObjValidater.validateReq(req);
    }

}
