package com.cdkj.ylq.api.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IAccountAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.dto.req.XN802300Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 分页查询账户
 * @author: xieyj 
 * @since: 2016年12月23日 下午8:19:09 
 * @history:
 */
public class XN802300 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802300Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public Object doBusiness() throws BizException {

        Account condition = new Account();

        condition.setUserId(req.getUserId());
        condition.setCurrency(req.getCurrency());
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setLastOrder(req.getLastOrder());
        condition.setMobileForQuery(req.getMobileForQuery());
        condition.setRelaNameForQuery(req.getRelaNameForQuery());

        condition.setAccountNumber(req.getAccountNumber());
        if (CollectionUtils.isNotEmpty(req.getCurrencyList())) {
            condition.setCurrencyList(req.getCurrencyList());
        }

        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IAccountAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return accountAO.queryAccountPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        // TODO Auto-generated method stub
        req = JsonUtil.json2Bean(inputparams, XN802300Req.class);
        ObjValidater.validateReq(req);
    }
}
