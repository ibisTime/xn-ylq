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
import com.cdkj.ylq.dto.req.XN623101Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 关闭/开启
 * @author: haiqingzheng 
 * @since: 2017年8月16日 上午11:32:21 
 * @history:
 */
public class XN623101 extends AProcessor {

    private ICouponAO couponAO = SpringContextHolder.getBean(ICouponAO.class);

    private XN623101Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        couponAO.openClose(req.getCode(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623101Req.class);
        StringValidater.validateBlank(req.getCode(), req.getUpdater());
    }

}
