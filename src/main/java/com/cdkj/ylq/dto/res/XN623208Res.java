package com.cdkj.ylq.dto.res;

import java.util.Date;

public class XN623208Res {

    // 手机
    private String mobile;

    // 渠道名称
    private String wayName;

    // 注册时间
    private Date regDatetime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public Date getRegDatetime() {
        return regDatetime;
    }

    public void setRegDatetime(Date regDatetime) {
        this.regDatetime = regDatetime;
    }
}
