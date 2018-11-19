package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.PhoneUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805041Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 前端用户普通注册
 * @author: myb858 
 * @since: 2015年8月23日 上午11:42:00
 * @history:
 */
public class XN805041 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805041Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        return userAO.doRegister(req.getMobile(), req.getLoginPwd(),
            req.getUserReferee(), req.getUserRefereeKind(),
            req.getSmsCaptcha(), req.getProvince(), req.getCity(),
            req.getArea(), req.getAddress(), req.getCompanyCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805041Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getLoginPwd(),
            req.getSmsCaptcha(), req.getCompanyCode());
        PhoneUtil.checkMobile(req.getMobile());// 判断格式
    }
}
