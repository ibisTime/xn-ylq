package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805065Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * admin管理员重置密码
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:19:27 
 * @history:
 */
public class XN805065 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805065Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetLoginPwdByOss(req.getUserId(), req.getLoginPwd(),
            req.getAdminUserId(), req.getAdminPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805065Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getLoginPwd(),
            req.getAdminUserId(), req.getAdminPwd());
    }
}
