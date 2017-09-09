/**
 * @Title MxCarrierNofification.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月9日 上午10:46:16 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月9日 上午10:46:16 
 * @history:
 */
public class MxCarrierNofification {

    // 手机号
    private String mobile;

    // 姓名
    private String name;

    // 身份证号
    private String idcard;

    // UNIX timestamp(毫秒)
    private Long timestamp;

    // 报告结果. true - 成功; false - 失败
    private String result;

    // 成功时为前台界面展示的加密请求报文，失败时为失败原因
    private String message;

    // 任务ID
    private String task_id;

    // 合作机构的用户ID
    private String user_id;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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

}
