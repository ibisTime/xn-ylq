package com.cdkj.ylq.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IProductDAO;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.exception.BizException;

@Component
public class ProductBOImpl extends PaginableBOImpl<Product> implements
        IProductBO {

    @Autowired
    private IProductDAO productDAO;

    @Override
    public String saveProduct(Product data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generateM(EGeneratePrefix.PRODUCT.getCode());
            data.setCode(code);
            productDAO.insert(data);
        }
        return code;
    }

    @Override
    public int refreshProduct(Product data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = productDAO.updateProduct(data);
        }
        return count;
    }

    @Override
    public Product getProduct(String code) {
        Product data = null;
        if (StringUtils.isNotBlank(code)) {
            Product condition = new Product();
            condition.setCode(code);
            data = productDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "产品编号不存在");
            }
        }
        return data;
    }
}
