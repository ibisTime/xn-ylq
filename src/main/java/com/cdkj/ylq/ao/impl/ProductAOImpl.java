package com.cdkj.ylq.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IProductAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN623000Req;
import com.cdkj.ylq.dto.req.XN623001Req;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EProductLevel;
import com.cdkj.ylq.enums.EProductLocation;
import com.cdkj.ylq.enums.EProductStatus;
import com.cdkj.ylq.enums.EUserProductStatus;
import com.cdkj.ylq.exception.BizException;

@Service
public class ProductAOImpl implements IProductAO {

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IApplyBO applyBO;

    @Override
    public String addProduct(XN623000Req req) {
        Product data = new Product();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.PRODUCT
            .getCode());

        data.setCode(code);
        data.setName(req.getName());
        data.setSlogan(req.getSlogan());
        data.setLevel(req.getLevel());
        data.setAmount(StringValidater.toLong(req.getAmount()));

        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setRate1(StringValidater.toDouble(req.getRate1()));
        data.setRate2(StringValidater.toDouble(req.getRate2()));
        data.setLxAmount(StringValidater.toLong(req.getLxAmount()));
        data.setXsAmount(StringValidater.toLong(req.getXsAmount()));

        data.setGlAmount(StringValidater.toLong(req.getGlAmount()));
        data.setFwAmount(StringValidater.toLong(req.getFwAmount()));
        data.setStatus(EProductStatus.NEW.getCode());
        data.setUiLocation(EProductLocation.NORMAL.getCode());
        data.setUiOrder(0);
        data.setUiColor(EProductLevel.getLevelCode(req.getLevel()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        productBO.saveProduct(data);
        return code;
    }

    @Override
    public int editProduct(XN623001Req req) {
        Product data = new Product();

        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setSlogan(req.getSlogan());
        data.setLevel(req.getLevel());
        data.setAmount(StringValidater.toLong(req.getAmount()));

        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setRate1(StringValidater.toDouble(req.getRate1()));
        data.setRate2(StringValidater.toDouble(req.getRate2()));
        data.setLxAmount(StringValidater.toLong(req.getLxAmount()));
        data.setXsAmount(StringValidater.toLong(req.getXsAmount()));

        data.setGlAmount(StringValidater.toLong(req.getGlAmount()));
        data.setFwAmount(StringValidater.toLong(req.getFwAmount()));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        return productBO.refreshProduct(data);
    }

    @Override
    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition) {
        return productBO.getPaginable(start, limit, condition);
    }

    @Override
    public Paginable<Product> queryProductPage(int start, int limit,
            Product condition, String userId) {
        Paginable<Product> results = productBO.getPaginable(start, limit,
            condition);
        List<Product> products = results.getList();
        for (Product product : products) {
            if (StringUtils.isNotBlank(userId)) {
                User user = userBO.getRemoteUser(userId);
                Apply apply = applyBO
                    .getCurrentApply(userId, product.getCode());
                if (apply != null) {
                    product.setUserProductStatus(apply.getStatus());
                } else {
                    product.setUserProductStatus(EUserProductStatus.TO_APPLY
                        .getCode());
                }
                // to 根据用户等级 判断是否锁定产品
                if (EProductLevel.ONE.getCode().equals(product.getLevel())) {
                    product.setIsLocked(EBoolean.NO.getCode());
                } else {
                    product.setIsLocked(EBoolean.YES.getCode());
                }

            } else {
                product.setUserProductStatus(EUserProductStatus.TO_APPLY
                    .getCode());
                if (EProductLevel.ONE.getCode().equals(product.getLevel())) {
                    product.setIsLocked(EBoolean.NO.getCode());
                } else {
                    product.setIsLocked(EBoolean.YES.getCode());
                }
            }
        }
        return results;
    }

    @Override
    public Product getProduct(String code) {
        return productBO.getProduct(code);
    }

    @Override
    public int putOn(String code, String uiLocation, int uiOrder,
            String uiColor, String updater, String remark) {
        Product data = productBO.getProduct(code);
        if (!EProductStatus.NEW.getCode().equals(data.getStatus())
                && !EProductStatus.PUT_OFF.getCode().equals(data.getStatus())) {
            throw new BizException("xn623001", "产品不处于可上架的状态，不能上架");
        }
        return productBO.putOn(data, uiLocation, uiOrder, uiColor, updater,
            remark);
    }

    @Override
    public int putOff(String code, String updater, String remark) {
        Product data = productBO.getProduct(code);
        if (!EProductStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn623001", "产品不处于上架中的状态，不能下架");
        }
        return productBO.putOff(data, updater, remark);
    }

}
