/**
 * @Title XN798019Res.java 
 * @Package com.std.certi.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年7月27日 下午1:16:25 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.res;

import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2017年7月27日 下午1:16:25 
 * @history:
 */
public class XN798021Res {

    // 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
    private String bizNo;

    // 欺诈关注清单的RiskCode列表，对应的描述见产品文档
    private List<String> riskCodeList;

    // 欺诈关注清单的RiskCode列表转义
    private List<String> riskInofList;

    // 欺诈关注清单是否命中，yes标识命中，no标识未命中
    private String hit;

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public List<String> getRiskCodeList() {
        return riskCodeList;
    }

    public void setRiskCodeList(List<String> riskCodeList) {
        this.riskCodeList = riskCodeList;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public List<String> getRiskInofList() {
        return riskInofList;
    }

    public void setRiskInofList(List<String> riskInofList) {
        this.riskInofList = riskInofList;
    }

}
