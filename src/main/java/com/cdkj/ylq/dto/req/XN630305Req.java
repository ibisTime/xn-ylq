/**
 * @Title XN630305Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午7:01:35 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * 分页查询公司
 * @author: dl 
 * @since: 2018年8月17日 下午7:01:35 
 * @history:
 */
public class XN630305Req extends APageReq {

    private static final long serialVersionUID = 3996763398737489440L;

    // 公司名称
    private String name;

    // 更新人
    private String updater;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
