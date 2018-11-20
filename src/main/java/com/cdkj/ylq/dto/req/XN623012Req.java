/**
 * @Title XN623000Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月11日 下午2:47:20 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月11日 下午2:47:20 
 * @history:
 */
public class XN623012Req extends APageReq {

    private static final long serialVersionUID = 1845471254089653851L;

    // 用户编号（必填）
    private String userId;

    // 公司编号
    private String comapnyCode;

    public String getComapnyCode() {
        return comapnyCode;
    }

    public void setComapnyCode(String comapnyCode) {
        this.comapnyCode = comapnyCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
