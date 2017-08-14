/**
 * @Title XN623000.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月11日 下午2:45:38 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IApplyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623031Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 详情查询审核记录
 * @author: haiqingzheng 
 * @since: 2017年8月11日 下午2:45:38 
 * @history:
 */
public class XN623031 extends AProcessor {

    private IApplyAO applyAO = SpringContextHolder.getBean(IApplyAO.class);

    private XN623031Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return applyAO.getApply(req.getCode());
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623031Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
