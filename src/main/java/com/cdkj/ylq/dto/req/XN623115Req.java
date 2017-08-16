/**
 * @Title XN623100Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 上午11:33:10 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月16日 上午11:33:10 
 * @history:
 */
public class XN623115Req extends APageReq {

    private static final long serialVersionUID = -1067088502777119552L;

    // 类型（选填）
    private String type;

    // 状态（选填）
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
