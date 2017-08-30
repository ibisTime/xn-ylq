/**
 * @Title XN623070.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 下午4:31:31 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IBorrowAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.dto.req.XN623085Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 分页查询借款订单
 * @author: haiqingzheng 
 * @since: 2017年8月16日 下午4:31:31 
 * @history:
 */
public class XN623085 extends AProcessor {

    private IBorrowAO borrowAO = SpringContextHolder.getBean(IBorrowAO.class);

    private XN623085Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Borrow condition = new Borrow();
        condition.setCodeForQuery(req.getCode());
        condition.setApplyUser(req.getApplyUser());
        condition.setStatus(req.getStatus());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IBorrowAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return borrowAO.queryBorrowPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623085Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
