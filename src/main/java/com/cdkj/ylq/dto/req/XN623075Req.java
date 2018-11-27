/**
 * @Title XN623070Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 下午5:03:02 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月16日 下午5:03:02 
 * @history:
 */
public class XN623075Req {

    // 借款编号（必填）
    @NotBlank
    private String code;

    // 规则编号（必填）
    @NotBlank
    private String stageRuleCode;

    // 审核人（必填）
    @NotBlank
    private String updater;

    // 审核说明（必填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStageRuleCode() {
        return stageRuleCode;
    }

    public void setStageRuleCode(String stageRuleCode) {
        this.stageRuleCode = stageRuleCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
