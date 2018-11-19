package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805063Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 重置登录密码
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:03:24 
 * @history:
 */
public class XN805063 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805063Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetLoginPwd(req.getMobile(), req.getSmsCaptcha(),
            req.getNewLoginPwd(), req.getCompanyCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805063Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getSmsCaptcha(),
            req.getNewLoginPwd(), req.getCompanyCode());
    }
}
