package com.cdkj.ylq.api.impl;

import java.util.Date;

import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;

/**
 * 获取服务器时间
 * @author: myb858 
 * @since: 2015年11月26日 下午5:18:23 
 * @history:
 */
public class XNlh9001 extends AProcessor {

    @Override
    public Object doBusiness() throws BizException {
        return new Date();
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
    }

}
