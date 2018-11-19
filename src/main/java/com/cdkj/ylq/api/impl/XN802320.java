package com.cdkj.ylq.api.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IJourAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Jour;
import com.cdkj.ylq.dto.req.XN802320Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 流水分页查询(oss)
 * @author: xieyj 
 * @since: 2016年12月24日 上午7:59:19 
 * @history:
 */
public class XN802320 extends AProcessor {

    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN802320Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        Jour condition = new Jour();

        condition.setType(req.getType());
        condition.setUserId(req.getUserId());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setAccountType(req.getAccountType());
        condition.setCurrency(req.getCurrency());

        condition.setBizType(req.getBizType());
        condition.setStatus(req.getStatus());
        condition.setChannelType(req.getChannelType());
        condition.setChannelOrder(req.getChannelOrder());
        condition.setRefNo(req.getRefNo());

        condition.setRelaNameForQuery(req.getRelaNameForQuery());

        if (CollectionUtils.isNotEmpty(req.getCurrencyList())) {
            condition.setCurrencyList(req.getCurrencyList());
        }

        condition.setCreateDatetimeStart(DateUtil.strToDate(
            req.getCreateDatetimeStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setCreateDatetimeEnd(DateUtil.getEndDatetime(req
            .getCreateDatetimeEnd()));

        condition.setWorkDate(req.getWorkDate());
        condition.setCheckUser(req.getCheckUser());
        condition.setAdjustUser(req.getAdjustUser());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IJourAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return jourAO.queryJourPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802320Req.class);
        ObjValidater.validateReq(req);
    }
}
