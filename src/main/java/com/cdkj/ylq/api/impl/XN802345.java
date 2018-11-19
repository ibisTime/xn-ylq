package com.cdkj.ylq.api.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IChargeAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Charge;
import com.cdkj.ylq.dto.req.XN802345Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 充值订单分页查询
 * @author: xieyj 
 * @since: 2017年5月13日 下午7:27:55 
 * @history:
 */
public class XN802345 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802345Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Charge condition = new Charge();

        condition.setCodeForQuery(req.getCode());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setAccountType(req.getAccountType());
        condition.setCurrency(req.getCurrency());
        if (CollectionUtils.isNotEmpty(req.getCurrencyList())) {
            condition.setCurrencyList(req.getCurrencyList());
        }
        condition.setBizType(req.getBizType());
        condition.setPayCardNo(req.getPayCardNo());

        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getApplyUser());
        condition.setApplyDatetimeStart(DateUtil.getFrontDate(
            req.getApplyDateStart(), false));
        condition.setApplyDatetimeEnd(DateUtil.getFrontDate(
            req.getApplyDateEnd(), true));
        condition.setPayUser(req.getPayUser());

        condition.setPayDatetimeStart(DateUtil.getFrontDate(
            req.getPayDateStart(), false));
        condition.setPayDatetimeEnd(DateUtil.getFrontDate(req.getPayDateEnd(),
            true));
        condition.setChannelType(req.getChannelType());
        condition.setChannelOrder(req.getChannelOrder());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IChargeAO.DEFAULT_ORDER_COLUMN;
        }

        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return chargeAO.queryChargePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802345Req.class);
        ObjValidater.validateReq(req);
    }
}
