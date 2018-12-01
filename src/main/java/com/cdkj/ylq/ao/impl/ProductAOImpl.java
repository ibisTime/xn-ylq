package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IProductAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IBorrowOrderBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.dto.req.XN623000Req;
import com.cdkj.ylq.dto.req.XN623001Req;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EProductLevel;
import com.cdkj.ylq.enums.EProductLocation;
import com.cdkj.ylq.enums.EProductStatus;
import com.cdkj.ylq.exception.BizException;

@Service
public class ProductAOImpl implements IProductAO {

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IApplyBO applyBO;

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private IBorrowOrderBO borrowOrderBO;

    @Override
    public String addProduct(XN623000Req req) {
        Product data = new Product();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.PRODUCT
            .getCode());

        data.setCode(code);
        data.setName(req.getName());
        data.setSlogan(req.getSlogan());
        data.setLevel(req.getLevel());
        data.setAmount(StringValidater.toBigDecimal(req.getAmount()));

        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setYqRate1(StringValidater.toBigDecimal(req.getYqRate1()));
        data.setYqRate2(StringValidater.toBigDecimal(req.getYqRate2()));
        data.setLxRate(StringValidater.toBigDecimal(req.getLxRate()));
        data.setXsAmount(StringValidater.toBigDecimal(req.getXsAmount()));

        data.setGlAmount(StringValidater.toBigDecimal(req.getGlAmount()));
        data.setFwAmount(StringValidater.toBigDecimal(req.getFwAmount()));
        data.setStatus(EProductStatus.OFF.getCode());
        data.setLocation(EProductLocation.NORMAL.getCode());
        data.setColor(EProductLevel.getLevelCode(req.getLevel()));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setCompanyCode(req.getCompanyCode());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));

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
        data.setAmount(StringValidater.toBigDecimal(req.getAmount()));

        data.setDuration(StringValidater.toInteger(req.getDuration()));
        data.setYqRate1(StringValidater.toBigDecimal(req.getYqRate1()));
        data.setYqRate2(StringValidater.toBigDecimal(req.getYqRate2()));
        data.setLxRate(StringValidater.toBigDecimal(req.getLxRate()));
        data.setXsAmount(StringValidater.toBigDecimal(req.getXsAmount()));
        data.setColor(EProductLevel.getLevelCode(req.getLevel()));

        data.setGlAmount(StringValidater.toBigDecimal(req.getGlAmount()));
        data.setFwAmount(StringValidater.toBigDecimal(req.getFwAmount()));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));

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
        if (!StringUtils.isBlank(userId)) {

            Certification certification = certificationBO.getCertification(
                userId, ECertiKey.INFO_AMOUNT);
            InfoAmount infoAmount = JsonUtil.json2Bean(
                certification.getResult(), InfoAmount.class);
            for (Product product : products) {

                if (infoAmount.getSxAmount().compareTo(product.getAmount()) >= 0) {
                    product.setIsLocked(EBoolean.NO.getCode());
                } else {
                    product.setIsLocked(EBoolean.YES.getCode());
                }

            }
        } else {
            for (Product product : products) {
                product.setIsLocked(EBoolean.YES.getCode());
            }
        }

        return results;
    }

    @Override
    public Product getProduct(String code) {

        Product product = productBO.getProduct(code);
        // 借款总额
        BigDecimal borrowAmount = product.getAmount();
        // 利息(进位保留两位小数)
        BigDecimal lxAmount = borrowAmount
            .multiply(
                product.getLxRate().multiply(
                    new BigDecimal(product.getDuration()))).setScale(2,
                BigDecimal.ROUND_UP);
        product.setLxAmount(lxAmount);

        return product;
    }

    @Override
    public int putOn(String code, String uiLocation, int uiOrder,
            String uiColor, String updater, String remark) {
        Product data = productBO.getProduct(code);
        if (!EProductStatus.OFF.getCode().equals(data.getStatus())) {
            throw new BizException("xn623001", "产品不处于可开启的状态，不能上架");
        }
        return productBO.putOn(data, uiLocation, uiOrder, uiColor, updater,
            remark);
    }

    @Override
    public Product getAvaliableProduct(String userId) {
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        if (certification == null) {
            throw new BizException("623013", "您还没有额度，请先选择产品进行申请");
        }
        // 授信额度
        InfoAmount infoAmount = JsonUtil.json2Bean(certification.getResult(),
            InfoAmount.class);
        if (infoAmount.getSxAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("623013", "您还没有额度，请先选择产品进行申请");
        }
        if (StringUtils.isBlank(certification.getRef())) {
            throw new BizException("623013", "您还没有额度，请先选择产品进行申请");
        }
        // 是否已经有借款
        BorrowOrder borrow = borrowOrderBO.getCurrentBorrow(userId);
        if (borrow != null) {
            throw new BizException("623013", "当前已有借款");
        }
        Product product = productBO.getProduct(certification.getRef());
        // 借款总额
        BigDecimal borrowAmount = product.getAmount();
        // 利息(进位保留两位小数)
        BigDecimal lxAmount = borrowAmount
            .multiply(
                product.getLxRate().multiply(
                    new BigDecimal(product.getDuration()))).setScale(2,
                BigDecimal.ROUND_UP);
        product.setLxAmount(lxAmount);
        return product;
    }

    @Override
    public int putOff(String code, String updater, String remark) {
        Product data = productBO.getProduct(code);
        if (!EProductStatus.ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn623001", "产品不处于开启中的状态，不能关闭");
        }
        return productBO.putOff(data, updater, remark);
    }

}
