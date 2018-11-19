package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IChannelBankAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN802117Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 详情查银行
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN802117 extends AProcessor {

    private IChannelBankAO channelBankAO = SpringContextHolder
        .getBean(IChannelBankAO.class);

    private XN802117Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return channelBankAO
            .getChannelBank(StringValidater.toLong(req.getId()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802117Req.class);
        ObjValidater.validateReq(req);
    }

}
