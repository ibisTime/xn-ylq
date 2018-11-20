/**
 * @Title XN630091.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午4:15:48 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.QnTokenImpl;
import com.cdkj.ylq.dto.res.XN805951Res;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * @author: taojian 
 * @since: 2018年11月20日 下午4:15:48 
 * @history:
 */
public class XN630091 extends AProcessor {

    private QnTokenImpl qnTokenImpl = SpringContextHolder
        .getBean(QnTokenImpl.class);

    @Override
    public Object doBusiness() throws BizException {
        return new XN805951Res(qnTokenImpl.getUploadToken(ESystemCode.YLQ
            .getCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        // TODO Auto-generated method stub

    }

}
