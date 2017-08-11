package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IProductAO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Product;

@Service
public class ProductAOImpl implements IProductAO {

    @Autowired
    private IProductBO productBO;

    @Override
    public String addProduct(Product data) {
        return productBO.saveProduct(data);
    }

    @Override
    public int editProduct(Product data) {
        return productBO.refreshProduct(data);
    }

    @Override
    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition) {
        return productBO.getPaginable(start, limit, condition);
    }

    @Override
    public Product getProduct(String code) {
        return productBO.getProduct(code);
    }
}
