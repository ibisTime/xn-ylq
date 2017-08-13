/**
 * @Title XN798019Res.java 
 * @Package com.std.certi.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年7月27日 下午1:16:25 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年7月27日 下午1:16:25 
 * @history:
 */
public class XN798019Res {
    // 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
    private String bizNo;

    // 申请欺诈评分，分数范围是[0,100]的整数,分数越高信息越真实
    private Long score;

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

}
