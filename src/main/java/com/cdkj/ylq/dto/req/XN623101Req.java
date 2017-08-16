/**
 * @Title XN623100Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 上午11:33:10 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月16日 上午11:33:10 
 * @history:
 */
public class XN623101Req {

    // 编号（必填）
    private String code;

    // 最后更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
