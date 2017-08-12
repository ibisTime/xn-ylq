/**
 * @Title XN623000.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月11日 下午2:45:38 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IProductAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.dto.req.XN623012Req;
import com.cdkj.ylq.enums.EProductLocation;
import com.cdkj.ylq.enums.EProductStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * C端用户分页查询产品
 * @author: haiqingzheng 
 * @since: 2017年8月11日 下午2:45:38 
 * @history:
 */
public class XN623012 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN623012Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Product condition = new Product();
        condition.setStatus(EProductStatus.PUT_ON.getCode());
        condition.setUiLocation(EProductLocation.NORMAL.getCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IProductAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return productAO.queryProductPage(start, limit, condition,
            req.getUserId());
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623012Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
