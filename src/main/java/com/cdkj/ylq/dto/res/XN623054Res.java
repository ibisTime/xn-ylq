/**
 * @Title XN623054Res.java 
 * @Package com.cdkj.ylq.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月20日 上午11:05:23 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月20日 上午11:05:23 
 * @history:
 */
public class XN623054Res {
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
