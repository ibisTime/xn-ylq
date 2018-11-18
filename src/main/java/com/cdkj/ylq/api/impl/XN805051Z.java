package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805051ZReq;
import com.cdkj.ylq.dto.res.XN805051ZRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 验证码登录注册
 * @author: xieyj 
 * @since: 2017年1月19日 下午5:52:48 
 * @history:
 */
public class XN805051Z extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805051ZReq req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805051ZRes(userAO.doCaptchaLoginReg(req.getMobile(),
            req.getKind(), req.getSmsCaptcha(), req.getCompanyCode(),
            req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805051ZReq.class);
        StringValidater.validateBlank(req.getMobile(), req.getKind(),
            req.getSmsCaptcha(), req.getSystemCode());
    }
}
