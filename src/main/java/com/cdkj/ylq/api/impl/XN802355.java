package com.cdkj.ylq.api.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IWithdrawAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Withdraw;
import com.cdkj.ylq.dto.req.XN802355Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 分页查询取现订单
 * @author: xieyj 
 * @since: 2017年5月17日 下午5:17:47 
 * @history:
 */
public class XN802355 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802355Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Withdraw condition = new Withdraw();
        if (StringUtils.isNotBlank(req.getAccountNumber())) {
            condition.setAccountNumber(req.getAccountNumber());
        }
        condition.setCodeForQuery(req.getCode());
        condition.setCurrency(req.getCurrency());

        if (CollectionUtils.isNotEmpty(req.getCurrencyList())) {
            condition.setCurrencyList(req.getCurrencyList());
        }

        condition.setAccountType(req.getAccountType());
        condition.setChannelType(req.getChannelType());
        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getApplyUser());
        condition.setApproveUser(req.getApproveUser());

        condition.setPayUser(req.getPayUser());
        condition.setChannelOrder(req.getChannelOrder());
        condition.setRealName(req.getRealName());

        condition.setApplyDatetimeStart(DateUtil.getFrontDate(
            req.getApplyDateStart(), false));
        condition.setApplyDatetimeEnd(DateUtil.getFrontDate(
            req.getApplyDateEnd(), true));

        condition.setApproveDatetimeStart(DateUtil.getFrontDate(
            req.getApproveDateStart(), false));
        condition.setApproveDatetimeEnd(DateUtil.getFrontDate(
            req.getApproveDateEnd(), true));

        condition.setPayDatetimeStart(DateUtil.getFrontDate(
            req.getPayDateStart(), false));
        condition.setPayDatetimeEnd(DateUtil.getFrontDate(req.getPayDateEnd(),
            true));

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IWithdrawAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return withdrawAO.queryWithdrawPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802355Req.class);
        ObjValidater.validateReq(req);

    }

}
