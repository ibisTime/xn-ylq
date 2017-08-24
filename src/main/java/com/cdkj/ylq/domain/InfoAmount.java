/**
 * @Title InfoAmount.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月13日 下午4:13:03 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

import java.util.Date;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月13日 下午4:13:03 
 * @history:
 */
public class InfoAmount {

    // 授信额度
    private Long sxAmount;

    // 标识
    private String flag;

    // 授信时间
    private Date cerDatetime;

    // 有效时间
    private Date validDatetime;

    // 剩余有效天数
    private Integer validDays;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getCerDatetime() {
        return cerDatetime;
    }

    public void setCerDatetime(Date cerDatetime) {
        this.cerDatetime = cerDatetime;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Date getValidDatetime() {
        return validDatetime;
    }

    public void setValidDatetime(Date validDatetime) {
        this.validDatetime = validDatetime;
    }

    public Long getSxAmount() {
        return sxAmount;
    }

    public void setSxAmount(Long sxAmount) {
        this.sxAmount = sxAmount;
    }
}
