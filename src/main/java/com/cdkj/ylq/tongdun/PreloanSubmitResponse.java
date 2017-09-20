package com.cdkj.ylq.tongdun;

import java.io.Serializable;

/**
 * @author: haiqingzheng 
 * @since: 2017年9月19日 下午4:28:21 
 * @history:
 */
public class PreloanSubmitResponse implements Serializable {

    private static final long serialVersionUID = 4152462611121573434L;

    private Boolean success = false;

    private String report_id;

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
            .append("success", success).append("report_id", report_id)
            .toString();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

}
