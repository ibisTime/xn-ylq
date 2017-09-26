/**
 * @Title InfoTongDunPreLoan.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月26日 上午11:18:26 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月26日 上午11:18:26 
 * @history:
 */
public class InfoTongDunPreLoan {

    // 同盾贷前审核返回结果
    private String tdData;

    // 个人基本信息
    private String personInfo;

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public String getTdData() {
        return tdData;
    }

    public void setTdData(String tdData) {
        this.tdData = tdData;
    }
}
