/**
 * @Title XN798012Req.java 
 * @Package com.std.certi.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月23日 下午9:34:09 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月23日 下午9:34:09 
 * @history:
 */
public class XN798015Req {
    // 系统编号(必填)
    private String systemCode;

    // 公司编号(必填)
    private String companyCode;

    // 身份证号（必填）
    private String idNo;

    // 姓名（必填）
    private String realName;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
