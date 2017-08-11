package com.cdkj.ylq.bo;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Product;

public interface IProductBO extends IPaginableBO<Product> {

    public String saveProduct(Product data);

    public int refreshProduct(Product data);

    public Product getProduct(String code);

}
