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
public class XN623043Req {

    // 用户编号（必填）
    private String userId;

    // 开户行（必填）
    private String bank;

    // 开户省市（必填）
    private String privinceCity;

    // 预留手机号（必填）
    private String mobile;

    // 银行卡号（必填）
    private String cardNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPrivinceCity() {
        return privinceCity;
    }

    public void setPrivinceCity(String privinceCity) {
        this.privinceCity = privinceCity;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
