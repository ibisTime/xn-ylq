/**
 * @Title XN798015Res.java 
 * @Package com.std.certi.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年7月26日 下午5:55:24 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.res;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.domain.ZmWatchListDetail;

/** 
 * @author: haiqingzheng 
 * @since: 2017年7月26日 下午5:55:24 
 * @history:
 */
public class XN798016Res extends XN798017Res {

    // 是否已授权
    private boolean authorized;

    // true=命中 在关注名单中 false=未命中
    private boolean isMatched;

    // 行业关注名单信息列表
    List<ZmWatchListDetail> details;

    // 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
    private String bizNo;

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean isMatched) {
        this.isMatched = isMatched;
    }

    public List<ZmWatchListDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ZmWatchListDetail> details) {
        this.details = details;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

}
