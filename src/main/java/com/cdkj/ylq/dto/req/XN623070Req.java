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
public class XN623070Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 优惠券ID
    private String couponId;

    // 产品编号
    @NotBlank
    private String productCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
