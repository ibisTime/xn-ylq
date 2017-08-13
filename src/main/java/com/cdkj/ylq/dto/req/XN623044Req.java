/**
 * @Title XN623041Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午8:31:49 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午8:31:49 
 * @history:
 */
public class XN623044Req {

    // 用户编号（必填）
    private String userId;

    // 身份证正面（必填）
    private String identifyPic;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdentifyPic() {
        return identifyPic;
    }

    public void setIdentifyPic(String identifyPic) {
        this.identifyPic = identifyPic;
    }
}
