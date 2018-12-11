/**
 * @Title DtReport.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author taojian  
 * @date 2018年12月11日 下午3:06:20 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: taojian 
 * @since: 2018年12月11日 下午3:06:20 
 * @history:
 */
public class DtReport {

    private boolean success;

    private String code;

    private String msg;

    private String data;

    private String fee;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
