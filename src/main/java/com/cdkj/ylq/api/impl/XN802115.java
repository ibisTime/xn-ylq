package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IChannelBankAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.ChannelBank;
import com.cdkj.ylq.dto.req.XN802115Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 分页查银行
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN802115 extends AProcessor {

    private IChannelBankAO channelBankAO = SpringContextHolder
        .getBean(IChannelBankAO.class);

    private XN802115Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ChannelBank condition = new ChannelBank();
        condition.setBankCode(req.getBankCode());
        condition.setBankName(req.getBankName());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IChannelBankAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return channelBankAO.queryChannelBankPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802115Req.class);
        ObjValidater.validateReq(req);
    }

}
