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
public class XN623031Req extends APageReq {

    private static final long serialVersionUID = 1845471254089653851L;

    // 申请记录编号（必填）
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
