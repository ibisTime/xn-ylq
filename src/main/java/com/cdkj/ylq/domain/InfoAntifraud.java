/**
 * @Title InfoAntifraud.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月13日 下午1:00:38 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月13日 下午1:00:38 
 * @history:
 */
public class InfoAntifraud {
    // 申请欺诈评分，分数范围是[0,100]的整数,分数越高信息越真实
    private Long score;

    // 欺诈信息验证，输出验证码verifyCode列表转义
    private List<String> verifyInfoList;

    // 欺诈关注清单是否命中，yes标识命中，no标识未命中
    private String hit;

    // 欺诈关注清单的RiskCode列表转义
    private List<String> riskInfoList;

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public List<String> getVerifyInfoList() {
        return verifyInfoList;
    }

    public void setVerifyInfoList(List<String> verifyInfoList) {
        this.verifyInfoList = verifyInfoList;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public List<String> getRiskInfoList() {
        return riskInfoList;
    }

    public void setRiskInfoList(List<String> riskInfoList) {
        this.riskInfoList = riskInfoList;
    }

}
