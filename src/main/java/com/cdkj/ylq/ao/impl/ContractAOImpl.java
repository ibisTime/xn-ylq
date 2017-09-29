/**
 * @Title ContractAOImpl.java 
 * @Package com.xnjr.pop.bo.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年11月14日 下午3:42:05 
 * @version V1.0   
 */
package com.cdkj.ylq.ao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IContractAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.IContractBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IUserCouponBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.AmountUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.Contract;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2015年11月14日 下午3:42:05 
 * @history:
 */
@Service
public class ContractAOImpl implements IContractAO {
    @Autowired
    private IContractBO contractBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IBorrowBO borrowBO;

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private IUserCouponBO userCouponBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String preview(String userId, Long couponId) {

        // 获取用户信息
        User user = userBO.getRemoteUser(userId);
        if (EBoolean.YES.getCode().equals(user.getBlacklistFlag())) {
            throw new BizException("xn000000", "由于您逾期未还款，已被平台拉入黑名单，请联系平台进行处理！");
        }
        Bankcard bankcard = accountBO.getBankcard(userId);
        // 授信额度信息校验
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        if (certification == null) {
            throw new BizException("623070", "您还没有额度，请先选择产品进行申请");
        }
        InfoAmount infoAmount = JsonUtil.json2Bean(certification.getResult(),
            InfoAmount.class);
        if (infoAmount.getSxAmount() == 0) {
            throw new BizException("623070", "您还没有额度，请先选择产品进行申请");
        }
        if (StringUtils.isBlank(certification.getRef())) {
            throw new BizException("623070", "您还没有额度，请先选择产品进行申请");
        }
        if (ECertificationStatus.INVALID.getCode().equals(
            certification.getFlag())) {
            throw new BizException("623070", "您的额度已失效，请选择产品重新申请");
        }
        // 是否已经有借款
        if (borrowBO.getCurrentBorrow(userId) != null) {
            throw new BizException("623070", "当前已有借款");
        }
        // 产品
        Product product = productBO.getProduct(certification.getRef());

        // 优惠金额
        Long yhAmount = 0L;
        if (couponId != null) {
            UserCoupon userCoupon = userCouponBO.getUserCoupon(couponId);
            if (!EUserCouponStatus.TO_USE.getCode().equals(
                userCoupon.getStatus())) {
                throw new BizException("623070", "优惠券已不可使用");
            }
            if (infoAmount.getSxAmount() < userCoupon.getStartAmount()) {
                throw new BizException("623070", "不可使用该优惠券");
            }
            yhAmount = userCoupon.getAmount();
        }
        // 借款总额
        Long borrowAmount = infoAmount.getSxAmount();
        // 利息
        Long lxAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getLxRate())) * product.getDuration();
        // 快速信审费
        Long xsAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getXsRate())) * product.getDuration();
        // 账户管理费
        Long glAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getGlRate())) * product.getDuration();
        // 服务费
        Long fwAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
            product.getFwRate())) * product.getDuration();
        // 应还金额
        Long totalAmount = borrowAmount;
        // 生成预览合同
        String contact = contractBO.preview(user, bankcard, product,
            borrowAmount);
        return contact;
    }

    @Override
    public Paginable<Contract> queryContractPage(int start, int limit,
            Contract condition) {
        return contractBO.getPaginable(start, limit, condition);
    }

    @Override
    public Contract getContract(String contractCode) {

        return contractBO.getContract(contractCode);
    }

}
