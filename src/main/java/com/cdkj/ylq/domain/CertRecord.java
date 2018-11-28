package com.cdkj.ylq.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 认证调用记录
* @author: taojian 
* @since: 2018-11-28 16:26:49
* @history:
*/
public class CertRecord extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // id
    private Long id;

    // 调用人
    private String userId;

    // 调用时间
    private Date useDatetime;

    // 费用
    private BigDecimal fee;

    // 认证类型
    private String certKey;

    // 公司编号
    private String companyCode;

    // ****************************8

    private Date useDatetimeStart;

    private Date useDatetimeEnd;

    public Date getUseDatetimeStart() {
        return useDatetimeStart;
    }

    public void setUseDatetimeStart(Date useDatetimeStart) {
        this.useDatetimeStart = useDatetimeStart;
    }

    public Date getUseDatetimeEnd() {
        return useDatetimeEnd;
    }

    public void setUseDatetimeEnd(Date useDatetimeEnd) {
        this.useDatetimeEnd = useDatetimeEnd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUseDatetime(Date useDatetime) {
        this.useDatetime = useDatetime;
    }

    public Date getUseDatetime() {
        return useDatetime;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setCertKey(String certKey) {
        this.certKey = certKey;
    }

    public String getCertKey() {
        return certKey;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

}
