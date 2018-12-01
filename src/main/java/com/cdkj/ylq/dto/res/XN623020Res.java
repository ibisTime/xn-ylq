/**
 * @Title XN623020Res.java 
 * @Package com.cdkj.ylq.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月31日 上午12:26:17 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.res;

import java.math.BigDecimal;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月31日 上午12:26:17 
 * @history:
 */
public class XN623020Res {
    // 申请记录编号
    private String code;

    // 申请状态
    private String status;

    private BigDecimal creditScore;

    public BigDecimal getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(BigDecimal creditScore) {
        this.creditScore = creditScore;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
