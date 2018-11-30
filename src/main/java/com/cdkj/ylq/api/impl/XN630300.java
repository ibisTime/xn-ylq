/**
 * @Title XN630300.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月30日 下午5:42:18 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICompanyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630300Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 新增公司子帐号
 * @author: taojian 
 * @since: 2018年11月30日 下午5:42:18 
 * @history:
 */
public class XN630300 extends AProcessor {

    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630300Req req;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(companyAO.addCompanyMan(req.getCompanyCode(),
            req.getRealName(), req.getMobile(), req.getLoginName(),
            req.getLoginPwd(), req.getRemark(), req.getRoleCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630300Req.class);
        ObjValidater.validateReq(req);
    }

}
