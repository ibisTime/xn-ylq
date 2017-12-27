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
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Statistic;
import com.cdkj.ylq.dto.req.XN623700Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 分页查询统计数据
 * @author: haiqingzheng 
 * @since: 2017年12月26日 下午8:10:00 
 * @history:
 */
public class XN623700 extends AProcessor {
    private IStatisticAO statisticAO = SpringContextHolder
        .getBean(IStatisticAO.class);

    private XN623700Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Statistic condition = new Statistic();
        condition.setOrder("id", "desc");
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return statisticAO.queryStatisticPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623700Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
