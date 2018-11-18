package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805040Req;
import com.cdkj.ylq.dto.res.XN805040Res;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 检查手机号是否存在
 * @author: myb858 
 * @since: 2016年1月24日 下午8:23:23 
 * @history:
 */
public class XN805040 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805040Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doCheckMobile(req.getMobile(), req.getKind(),
            req.getCompanyCode(), req.getSystemCode());
        return new XN805040Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805040Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getKind(),
            req.getCompanyCode(), req.getSystemCode());
    }
}
