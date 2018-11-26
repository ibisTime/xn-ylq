/**
 * @Title XN623000Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月11日 下午2:47:20 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月11日 下午2:47:20 
 * @history:
 */
public class XN623030Req extends APageReq {

    private static final long serialVersionUID = 1845471254089653851L;

    // 申请人（选填）
    private String applyUser;

    // 状态
    private String status;

    // 状态List（选填）
    private List<String> statusList;

    private String companyCode;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
