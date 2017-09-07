/**
 * @Title MXReport.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月7日 下午8:34:16 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月7日 下午8:34:16 
 * @history:
 */
public class MXReport {

    private List<CallContactetail> call_contact_detail;

    public List<CallContactetail> getCall_contact_detail() {
        return call_contact_detail;
    }

    public void setCall_contact_detail(
            List<CallContactetail> call_contact_detail) {
        this.call_contact_detail = call_contact_detail;
    }
}
