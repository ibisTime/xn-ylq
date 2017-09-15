/**
 * @Title BaofooPay.java 
 * @Package com.std.account.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月13日 下午4:44:55 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月13日 下午4:44:55 
 * @history:
 */
public class BaofooPayQuery {

    private String transBatchid;// 宝付批次号

    private String transNo;// 商户订单号

    public String getTransBatchid() {
        return transBatchid;
    }

    public void setTransBatchid(String transBatchid) {
        this.transBatchid = transBatchid;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

}
