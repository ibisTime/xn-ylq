/**
 * @Title XN623070Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 下午5:03:02 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;


/** 
 * @author: haiqingzheng 
 * @since: 2017年8月16日 下午5:03:02 
 * @history:
 */
public class XN623082Req extends APageReq {

    private static final long serialVersionUID = 3722601087921882256L;

    // 是否分期
    private String isStage;

    public String getIsStage() {
        return isStage;
    }

    public void setIsStage(String isStage) {
        this.isStage = isStage;
    }

}
