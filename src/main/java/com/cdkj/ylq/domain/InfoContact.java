/**
 * @Title InfoBasic.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午12:22:50 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午12:22:50 
 * @history:
 */
public class InfoContact {

    // 亲属关系
    private String familyRelation;

    // 亲属联系人手机号码
    private String familyMobile;

    // 社会关系
    private String societyRelation;

    // 社会联系人手机号码
    private String societyMobile;

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

}
