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

    // 有效时间
    private Date validDatetime;

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
