/**
 * @Title InfoIdentifyPic.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月13日 下午12:20:55 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月13日 下午12:20:55 
 * @history:
 */
public class InfoIdentifyPic {

    // 身份证正面
    private String identifyPic;

    // 身份证反面
    private String identifyPicReverse;

    // 手持身份证
    private String identifyPicHand;

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
