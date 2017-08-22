package com.cdkj.ylq.domain;

import com.cdkj.ylq.dao.base.ABaseDO;

/**
* 优惠券赠送条件
* @author: haiqingzheng
* @since: 2017年08月22日 10:07:28
* @history:
*/
public class CouponCondition extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID主键
    private Long id;

    // 用户编号
    private String userId;

    // 优惠券类型
    private String couponType;

    // 已有次数
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
