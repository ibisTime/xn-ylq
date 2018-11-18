package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805043Req;
import com.cdkj.ylq.dto.res.XN805042Res;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 申请注册
 * @author: xieyj 
 * @since: 2017年7月14日 下午11:03:23 
 * @history:
 */
public class XN805043 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805043Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805042Res(userAO.doApplyRegUser(req));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805043Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getKind(),
            req.getCompanyCode(), req.getSystemCode());
    }
}
