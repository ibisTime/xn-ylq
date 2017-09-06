/**
 * @Title XN623041Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午8:31:49 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午8:31:49 
 * @history:
 */
public class XN623042Req {

    // 用户编号（必填）
    private String userId;

    // 亲属关系（必填）
    private String familyRelation;

    // 亲属名字（必填）
    private String familyName;

    // 亲属联系人手机号码（必填）
    private String familyMobile;

    // 社会关系（必填）
    private String societyRelation;

    // 社会联系人名字（必填）
    private String societyName;

    // 社会联系人手机号码（必填）
    private String societyMobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public String getFamilyMobile() {
        return familyMobile;
    }

    public void setFamilyMobile(String familyMobile) {
        this.familyMobile = familyMobile;
    }

    public String getSocietyRelation() {
        return societyRelation;
    }

    public void setSocietyRelation(String societyRelation) {
        this.societyRelation = societyRelation;
    }

    public String getSocietyMobile() {
        return societyMobile;
    }

    public void setSocietyMobile(String societyMobile) {
        this.societyMobile = societyMobile;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }
}
