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
public class XN798021Req {
    // 系统编号(必填)
    private String systemCode;

    // 公司编号(必填)
    private String companyCode;

    // 身份证号（必填）
    private String idNo;

    // 姓名（必填）
    private String realName;

    // 手机号（选填）
    private String mobile;

    // 电子邮箱（选填）
    private String email;

    // 银行卡号（选填）
    private String bankCard;

    // 地址信息（选填）
    private String address;

    // ip地址（选填）
    private String ip;

    // 物理地址（选填）
    private String mac;

    // wifi的物理地址（选填）
    private String wifimac;

    // 国际移动设备标志（选填）
    private String imei;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getWifimac() {
        return wifimac;
    }

    public void setWifimac(String wifimac) {
        this.wifimac = wifimac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

}
