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

    // 身份证反面（必填）
    private String identifyPicReverse;

    // 手持身份证（必填）
    private String identifyPicHand;

    // 真名
    private String realName;

    // 身份证号
    private String idNo;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

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

    public String getIdentifyPicReverse() {
        return identifyPicReverse;
    }

    public void setIdentifyPicReverse(String identifyPicReverse) {
        this.identifyPicReverse = identifyPicReverse;
    }

    public String getIdentifyPicHand() {
        return identifyPicHand;
    }

    public void setIdentifyPicHand(String identifyPicHand) {
        this.identifyPicHand = identifyPicHand;
    }
}
