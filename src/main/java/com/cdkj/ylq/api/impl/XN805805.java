package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.ICNavigateAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.CNavigate;
import com.cdkj.ylq.domain.CNavigateConverter;
import com.cdkj.ylq.dto.req.XN805805Req;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 分页查询导航
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN805805 extends AProcessor {
    private ICNavigateAO cNavigateAO = SpringContextHolder
        .getBean(ICNavigateAO.class);

    private XN805805Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CNavigate condition = CNavigateConverter.converter(req);
        condition.setIsFront(EBoolean.NO.getCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICNavigateAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return cNavigateAO.queryCNavigatePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805805Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater
            .validateBlank(req.getCompanyCode(), req.getSystemCode());
    }
}
