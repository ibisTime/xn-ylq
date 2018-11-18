package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805069Req;
import com.cdkj.ylq.dto.res.XN805051Res;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 修改支付密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:30:11 
 * @history:
 */
public class XN805069 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805069Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doModifyTradePwd(req.getUserId(), req.getOldTradePwd(),
            req.getNewTradePwd());
        return new XN805051Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805069Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getOldTradePwd(),
            req.getNewTradePwd());

    }

}
