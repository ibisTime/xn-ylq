/**
 * @Title CertiBOImpl.java 
 * @Package com.cdkj.ylq.bo.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月13日 下午12:04:04 
 * @version V1.0   
 */
package com.cdkj.ylq.bo.impl;

import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ICertiBO;
import com.cdkj.ylq.domain.MxReportData;
import com.cdkj.ylq.dto.req.XN798552Req;
import com.cdkj.ylq.http.BizConnecter;
import com.cdkj.ylq.http.JsonUtils;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月13日 下午12:04:04 
 * @history:
 */
@Component
public class CertiBOImpl implements ICertiBO {

    @Override
    public MxReportData doMxReportDataGet(String taskId) {
        XN798552Req req = new XN798552Req();
        req.setTaskId(taskId);
        return BizConnecter.getBizData("798552", JsonUtils.object2Json(req),
            MxReportData.class);
    }

}
