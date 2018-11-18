package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805062Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 修改手机号(需支付密码)
 * @author: myb858 
 * @since: 2015年9月15日 下午2:36:27 
 * @history:
 */
public class XN805062 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805062Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doChangeMoblie(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha(), req.getTradePwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805062Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha(), req.getTradePwd());
    }
}
