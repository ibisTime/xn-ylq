package com.cdkj.ylq.domain;

import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 认证结果
* @author: haiqingzheng
* @since: 2017年08月11日 20:56:51
* @history:
*/
public class Certification extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private Long id;

    // 用户编号
    private String userId;

    // 键
    private String certiKey;

    // 标识
    private String flag;

    // 认证结果
    private String result;

    // 认证时间
    private Date cerDatetime;

    // 有效时间
    private Date validDatetime;

    // 关联申请单
    private String ref;

    // 认证信息
    private String message;

    private String companyCode;

    // *** 查询字段 ****

    // 当前时间
    private Date curDatetime;

    // 认证名
    private String name;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCurDatetime() {
        return curDatetime;
    }

    public void setCurDatetime(Date curDatetime) {
        this.curDatetime = curDatetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCertiKey() {
        return certiKey;
    }

    public void setCertiKey(String certiKey) {
        this.certiKey = certiKey;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCerDatetime() {
        return cerDatetime;
    }

    public void setCerDatetime(Date cerDatetime) {
        this.cerDatetime = cerDatetime;
    }

    public Date getValidDatetime() {
        return validDatetime;
    }

    public void setValidDatetime(Date validDatetime) {
        this.validDatetime = validDatetime;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
