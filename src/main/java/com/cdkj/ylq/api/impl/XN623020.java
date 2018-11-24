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
import com.cdkj.ylq.dto.req.XN623020Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 申请借款（针对产品，发放额度）
 * @author: haiqingzheng 
 * @since: 2017年8月12日 上午11:30:59 
 * @history:
 */
public class XN623020 extends AProcessor {
    private IApplyAO applyAO = SpringContextHolder.getBean(IApplyAO.class);

    private XN623020Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return applyAO.submitApply(req.getApplyUser(), req.getCompanyCode(),
            req.getRemark());
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623020Req.class);
        StringValidater.validateBlank(req.getApplyUser(), req.getCompanyCode());
    }

}
