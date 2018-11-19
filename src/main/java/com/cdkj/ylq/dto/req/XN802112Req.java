package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改银行
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN802112Req {
    // id
    @NotBlank(message = "id不能为空")
    private String id;

    // 银行简称
    @NotBlank(message = "银行简称不能为空")
    private String bankCode;

    // 银行名称
    @NotBlank(message = "银行名称不能为空")
    private String bankName;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUpdater() {
        return updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
