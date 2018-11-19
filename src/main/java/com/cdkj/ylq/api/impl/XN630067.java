package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN630067Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 获取系统用户详情
 * @author: nyc 
 * @since: 2018年7月29日 下午5:16:47 
 * @history:
 */
public class XN630067 extends AProcessor {

    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN630067Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysUserAO.getSYSUser(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630067Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
