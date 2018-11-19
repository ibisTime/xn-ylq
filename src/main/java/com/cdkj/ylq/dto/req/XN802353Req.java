package com.cdkj.ylq.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 取现支付
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:02:36 
 * @history:
 */
public class XN802353Req {

    // 取现订单编号(必填)
    @NotEmpty
    private List<String> codeList;

    // 支付回录人(必填)
    @NotBlank
    private String payUser;

    // 审核结果1 是 0 否(必填)
    @NotBlank
    private String payResult;

    // 支付回录说明
    private String payNote;

    // 支付渠道订单编号（支付渠道代表）
    private String channelOrder;

    // 转账手续费
    private String payFee;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public String getPayNote() {
        return payNote;
    }

    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }

    public String getChannelOrder() {
        return channelOrder;
    }

    public void setChannelOrder(String channelOrder) {
        this.channelOrder = channelOrder;
    }

    public String getPayFee() {
        return payFee;
    }

    public void setPayFee(String payFee) {
        this.payFee = payFee;
    }
}
