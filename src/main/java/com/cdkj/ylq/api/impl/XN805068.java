package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805068Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 重置支付密码(需实名认证)
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:29:05 
 * @history:
 */
public class XN805068 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805068Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetTradePwd(req.getUserId(), req.getNewTradePwd(),
            req.getSmsCaptcha(), req.getIdKind(), req.getIdNo());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805068Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getNewTradePwd(),
            req.getSmsCaptcha(), req.getIdKind(), req.getIdNo());
    }

}
