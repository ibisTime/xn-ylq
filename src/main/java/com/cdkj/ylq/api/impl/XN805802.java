package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICNavigateAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.CNavigate;
import com.cdkj.ylq.domain.CNavigateConverter;
import com.cdkj.ylq.dto.req.XN805802Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 修改导航
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN805802 extends AProcessor {
    private ICNavigateAO cNavigateAO = SpringContextHolder
        .getBean(ICNavigateAO.class);

    private XN805802Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CNavigate data = CNavigateConverter.converter(req);
        cNavigateAO.editCNavigate(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805802Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getType(), req.getStatus(), req.getLocation(),
            req.getOrderNo(), req.getBelong(), req.getCompanyCode());
    }
}
