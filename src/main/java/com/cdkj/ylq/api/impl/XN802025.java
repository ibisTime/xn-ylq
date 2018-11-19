package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IBankcardAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.dto.req.XN802025Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 分页查询银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:38:29 
 * @history:
 */
public class XN802025 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802025Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bankcard condition = new Bankcard();
        condition.setSystemCode(req.getSystemCode());
        condition.setUserId(req.getUserId());
        condition.setBankName(req.getBankName());
        condition.setBankcardNumber(req.getBankcardNumber());
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IBankcardAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return bankCardAO.queryBankcardPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802025Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit(),
            req.getSystemCode());
    }

}
