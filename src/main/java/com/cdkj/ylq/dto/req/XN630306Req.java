/**
 * @Title XN630306Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午7:06:05 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * 列表查询公司
 * @author: tao 
 * @since: 2018年8月17日 下午7:06:05 
 * @history:
 */
public class XN630306Req extends AListReq {

    private static final long serialVersionUID = -8535496995861696379L;

    // 公司名称
    private String name;

    // 更新人
    private String updater;

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
