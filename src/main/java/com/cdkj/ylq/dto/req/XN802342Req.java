package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 批量审批线下充值订单
 * @author: xieyj 
 * @since: 2017年5月12日 上午9:58:24 
 * @history:
 */
public class XN802342Req {

    @NotBlank
    private String amount;

    @NotBlank
    private String bizNote;

    @NotBlank
    private String updater;

    @NotBlank
    private String currency;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBizNote() {
        return bizNote;
    }

    public void setBizNote(String bizNote) {
        this.bizNote = bizNote;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
