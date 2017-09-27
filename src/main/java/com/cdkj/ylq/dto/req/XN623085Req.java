/**
 * @Title XN623070Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 下午5:03:02 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月16日 下午5:03:02 
 * @history:
 */
public class XN623085Req extends APageReq {

    private static final long serialVersionUID = -7585865863094749310L;

    // 编号（选填）
    private String code;

    // 申请人（选填）
    private String applyUser;

    // 状态（选填）
    private String status;

    // 是否归档（选填）
    private String isArchive;

    // 状态List（选填）
    private List<String> statusList;

    // 逾期天数起
    private String yqDaysStart;

    // 逾期天数止
    private String yqDaysEnd;

    // 是否逾期
    private String isOverdue;

    // 放款方式
    private String loanType;

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(String isArchive) {
        this.isArchive = isArchive;
    }

    public String getYqDaysStart() {
        return yqDaysStart;
    }

    public void setYqDaysStart(String yqDaysStart) {
        this.yqDaysStart = yqDaysStart;
    }

    public String getYqDaysEnd() {
        return yqDaysEnd;
    }

    public void setYqDaysEnd(String yqDaysEnd) {
        this.yqDaysEnd = yqDaysEnd;
    }

}
