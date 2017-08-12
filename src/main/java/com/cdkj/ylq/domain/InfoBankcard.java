/**
 * @Title InfoBasic.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午12:22:50 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午12:22:50 
 * @history:
 */
public class InfoBankcard {

    // 开户行
    private String bank;

    // 开户省市
    private String privinceCity;

    // 预留手机号
    private String mobile;

    // 银行卡号
    private String cardNo;

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
