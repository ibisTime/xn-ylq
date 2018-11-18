package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805064Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 修改登录密码
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:09:51 
 * @history:
 */
public class XN805064 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805064Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyLoginPwd(req.getUserId(), req.getOldLoginPwd(),
            req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805064Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getOldLoginPwd(),
            req.getNewLoginPwd());
    }

}
