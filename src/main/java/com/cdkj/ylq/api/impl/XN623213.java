/**
 * @Title XN623210.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2019年1月15日 上午11:39:58 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IRepayCardAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623213Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 结算
 * @author: taojian 
 * @since: 2019年1月15日 上午11:39:58 
 * @history:
 */
public class XN623213 extends AProcessor {

    private IRepayCardAO repayCardAO = SpringContextHolder
        .getBean(IRepayCardAO.class);

    private XN623213Req req;

    @Override
    public Object doBusiness() throws BizException {
        repayCardAO.settle(req.getCode(),
            StringValidater.toBigDecimal(req.getAmount()), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623213Req.class);
        ObjValidater.validateReq(req);
    }

}
