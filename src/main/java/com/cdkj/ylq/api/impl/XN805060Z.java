package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805060ZReq;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 微信绑定手机号
 * @author: xieyj 
 * @since: 2016年11月22日 下午8:14:23 
 * @history:
 */
public class XN805060Z extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805060ZReq req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doBindMoblie(req.getUserId(), req.getMobile(),
            req.getSmsCaptcha(), req.getIsSendSms());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805060ZReq.class);
        StringValidater.validateBlank(req.getUserId(), req.getMobile(),
            req.getSmsCaptcha(), req.getIsSendSms());
    }
}
