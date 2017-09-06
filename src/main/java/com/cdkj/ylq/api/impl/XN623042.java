/**
 * @Title XN623040.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午12:07:58 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.PhoneUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623042Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 提交紧急联系人
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午12:07:58 
 * @history:
 */
public class XN623042 extends AProcessor {

    private ICertificationAO certificationAO = SpringContextHolder
        .getBean(ICertificationAO.class);

    private XN623042Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        certificationAO.submitInfoContact(req);
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623042Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getFamilyRelation(),
            req.getFamilyMobile(), req.getSocietyRelation(),
            req.getSocietyMobile());
        PhoneUtil.checkMobile(req.getFamilyMobile(), "请填写正确的手机号码");
        PhoneUtil.checkMobile(req.getSocietyMobile(), "请填写正确的手机号码");
    }
}
