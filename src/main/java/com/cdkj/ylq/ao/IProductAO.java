package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.dto.req.XN623000Req;
import com.cdkj.ylq.dto.req.XN623001Req;

public interface IProductAO {

    static final String DEFAULT_ORDER_COLUMN = "ui_order";

    public String addProduct(XN623000Req req);

    public int editProduct(XN623001Req req);

    // 上架
    public int putOn(String code, String uiLocation, int uiOrder,
            String uiColor, String updater, String remark);

    // 下架
    public int putOff(String code, String updater, String remark);

    // OSS分页查询产品
    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition);

    // C端用户分页查询产品
    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition, String userId);

    // C端用户详情查询当前可借款的产品
    public Product getAvaliableProduct(String userId);

    public Product getProduct(String code);

}
