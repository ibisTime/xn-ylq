package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Product;

public interface IProductBO extends IPaginableBO<Product> {

    public int saveProduct(Product data);

    public int refreshProduct(Product data);

    public int putOn(Product data, String updater, String remark);

    public int putOff(Product data, String updater, String remark);

    public Product getProduct(String code);

    public List<Product> queryProductList(Product condition);

    public List<Product> getModelProducts();

}
