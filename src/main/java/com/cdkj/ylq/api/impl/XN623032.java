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
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.dto.req.XN623032Req;
import com.cdkj.ylq.dto.res.XN623032Res;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 获取用户当前正在进行中的申请记录
 * @author: haiqingzheng 
 * @since: 2017年8月11日 下午2:45:38 
 * @history:
 */
public class XN623032 extends AProcessor {

    private IApplyAO applyAO = SpringContextHolder.getBean(IApplyAO.class);

    private XN623032Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        XN623032Res res = new XN623032Res();
        res.setToApproveFlag(EBoolean.NO.getCode());
        Apply apply = applyAO.getCurrentApply(req.getUserId());
        if (apply != null
                && EApplyStatus.TO_APPROVE.getCode().equals(apply.getStatus())) {
            res.setToApproveFlag(EBoolean.YES.getCode());
        }
        return res;
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623032Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
