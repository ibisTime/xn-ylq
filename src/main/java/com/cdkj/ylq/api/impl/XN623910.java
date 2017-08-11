package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSConfigAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623910Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 修改系统参数
 * @author: xieyj 
 * @since: 2016年11月23日 下午5:54:40 
 * @history:
 */
public class XN623910 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN623910Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysConfigAO.editSYSConfig(StringValidater.toLong(req.getId()),
            req.getCvalue(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623910Req.class);
        StringValidater.validateBlank(req.getId(), req.getUpdater(),
            req.getCvalue());
    }

}
