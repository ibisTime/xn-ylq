package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805050Req;
import com.cdkj.ylq.dto.res.XN805050Res;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 普通登录
 * @author: xieyj 
 * @since: 2016年11月22日 下午3:39:17 
 * @history:
 */
public class XN805050 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805050Res(userAO.doLogin(req.getLoginName(),
            req.getLoginPwd(), req.getKind(), req.getCompanyCode(),
            req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805050Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getLoginPwd(),
            req.getKind(), req.getSystemCode());
    }
}
