package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805121Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 根据userId获取用户信息
 * @author: myb858 
 * @since: 2015年8月23日 下午1:48:57 
 * @history:
 */
public class XN805121 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805121Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.doGetUser(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805121Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
