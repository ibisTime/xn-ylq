package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.IProductDAO;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.enums.EProductStatus;
import com.cdkj.ylq.exception.BizException;

@Component
public class ProductBOImpl extends PaginableBOImpl<Product> implements
        IProductBO {

    @Autowired
    private IProductDAO productDAO;

    @Override
    public int saveProduct(Product data) {
        int count = 0;
        if (data != null) {
            count = productDAO.insert(data);
        }
        return count;
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

    @Override
    public int putOn(Product data, String uiLocation, int uiOrder,
            String uiColor, String updater, String remark) {
        data.setLocation(uiLocation);
        data.setOrderNo(uiOrder);
        data.setColor(uiColor);
        data.setStatus(EProductStatus.ON.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        return productDAO.updatePutOn(data);
    }

    @Override
    public int putOff(Product data, String updater, String remark) {
        data.setStatus(EProductStatus.OFF.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        return productDAO.updatePutOff(data);
    }

    @Override
    public List<Product> queryProductList(Product condition) {
        return productDAO.selectList(condition);
    }

}
