/**
 * @Title XN623700.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年12月26日 下午8:10:00 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IStatisticAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.domain.Statistic;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 列表查询统计数据
 * @author: haiqingzheng 
 * @since: 2017年12月26日 下午8:10:00 
 * @history:
 */
public class XN623701 extends AProcessor {
    private IStatisticAO statisticAO = SpringContextHolder
        .getBean(IStatisticAO.class);

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Statistic condition = new Statistic();
        condition.setOrder("id", "desc");
        return statisticAO.queryStatisticList(condition);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
    }

}
