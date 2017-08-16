/**
 * @Title XN623100.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 上午11:32:21 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.ICouponAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.dto.req.XN623115Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 分页查询优惠券
 * @author: haiqingzheng 
 * @since: 2017年8月16日 上午11:32:21 
 * @history:
 */
public class XN623115 extends AProcessor {

    private ICouponAO couponAO = SpringContextHolder.getBean(ICouponAO.class);

    private XN623115Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Coupon condition = new Coupon();
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICouponAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return couponAO.queryCouponPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623115Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
