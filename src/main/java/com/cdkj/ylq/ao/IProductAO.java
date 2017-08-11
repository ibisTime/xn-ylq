package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Product;

public interface IProductAO {
    static final String DEFAULT_ORDER_COLUMN = "ui_order";

    public String addProduct(Product data);

    public int editProduct(Product data);

    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition);

    public Product getProduct(String code);

}
