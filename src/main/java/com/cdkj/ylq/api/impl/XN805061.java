package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805061Req;
import com.cdkj.ylq.dto.res.XN805047Res;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 修改手机号
 * @author: xieyj 
 * @since: 2016年12月27日 下午9:01:00 
 * @history:
 */
public class XN805061 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805061Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doChangeMoblie(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha());
        return new XN805047Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805061Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha());
    }
}
