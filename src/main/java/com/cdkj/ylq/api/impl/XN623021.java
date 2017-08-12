/**
 * @Title XN623020.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 上午11:30:59 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IApplyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623021Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 取消借款申请
 * @author: haiqingzheng 
 * @since: 2017年8月12日 上午11:30:59 
 * @history:
 */
public class XN623021 extends AProcessor {
    private IApplyAO applyAO = SpringContextHolder.getBean(IApplyAO.class);

    private XN623021Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        applyAO.cancalApply(req.getApplyUser(), req.getProductCode());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623021Req.class);
        StringValidater.validateBlank(req.getApplyUser(), req.getProductCode());
    }

}
