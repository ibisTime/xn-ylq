package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805042Req;
import com.cdkj.ylq.dto.res.XN805042Res;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 管理端代注册
 * @author: myb858 
 * @since: 2015年11月10日 下午12:59:10 
 * @history:
 */
public class XN805042 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805042Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805042Res(userAO.doAddUser(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805042Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getKind(),
            req.getUpdater(), req.getCompanyCode(), req.getSystemCode());
    }
}
