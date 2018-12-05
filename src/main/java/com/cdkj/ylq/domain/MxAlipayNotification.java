package com.cdkj.ylq.domain;

public class MxAlipayNotification {

    // 任务id
    private String task_id;

    // 用户id
    private String user_id;

    // 时间类型
    private String type;

    // 支付宝账号的映射id
    private String mapping_id;

    // 支付宝账号
    private String account;

    // 结果
    private String result;

    // 信息描述
    private String message;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMapping_id() {
        return mapping_id;
    }

    public void setMapping_id(String mapping_id) {
        this.mapping_id = mapping_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
