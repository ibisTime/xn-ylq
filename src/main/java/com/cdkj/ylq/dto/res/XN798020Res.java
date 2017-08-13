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
public class XN798020Res {
    // 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
    private String bizNo;

    // 欺诈信息验证，输出验证码verifyCode列表
    private List<String> verifyCodeList;

    // 欺诈信息验证，输出验证码verifyCode列表转义
    private List<String> verifyInfoList;

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public List<String> getVerifyCodeList() {
        return verifyCodeList;
    }

    public void setVerifyCodeList(List<String> verifyCodeList) {
        this.verifyCodeList = verifyCodeList;
    }

    public List<String> getVerifyInfoList() {
        return verifyInfoList;
    }

    public void setVerifyInfoList(List<String> verifyInfoList) {
        this.verifyInfoList = verifyInfoList;
    }

}
