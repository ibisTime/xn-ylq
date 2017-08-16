/**
 * @Title XN623100.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 上午11:32:21 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICouponAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623116Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 详情查询优惠券
 * @author: haiqingzheng 
 * @since: 2017年8月16日 上午11:32:21 
 * @history:
 */
public class XN623116 extends AProcessor {

    private ICouponAO couponAO = SpringContextHolder.getBean(ICouponAO.class);

    private XN623116Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return couponAO.getCoupon(req.getCode());
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623116Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
