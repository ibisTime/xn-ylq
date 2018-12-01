/**
 * @Title ICertiBO.java 
 * @Package com.cdkj.ylq.bo 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月13日 上午11:44:31 
 * @version V1.0   
 */
package com.cdkj.ylq.bo;

import com.cdkj.ylq.domain.MxReportData;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月13日 上午11:44:31 
 * @history:
 */
public interface ICertiBO {

    // 魔蝎报告查询
    public MxReportData doMxReportDataGet(String taskId);
}
