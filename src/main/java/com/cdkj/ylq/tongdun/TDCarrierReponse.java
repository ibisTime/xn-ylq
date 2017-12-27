package com.cdkj.ylq.tongdun;

/**
 * 同盾运营商回调结果解析
 * @author: chenshan 
 * @since: 2017年12月13日 上午11:26:47 
 * @history:
 */
public class TDCarrierReponse {
    // 返回码
    private String code;

    //
    private String message;

    // 任务编码。每个任务都有唯一的任务编 码
    private String task_id;

    private TDCarrierData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public TDCarrierData getData() {
        return data;
    }

    public void setData(TDCarrierData data) {
        this.data = data;
    }
}
