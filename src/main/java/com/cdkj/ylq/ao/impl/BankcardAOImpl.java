package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IBankcardAO;
import com.cdkj.ylq.bo.IBankcardBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.dto.req.XN802020Req;
import com.cdkj.ylq.dto.req.XN802022Req;
import com.cdkj.ylq.exception.BizException;

/**
 * 
 * @author: lei 
 * @since: 2018年9月11日 下午5:41:20 
 * @history:
 */
@Service
public class BankcardAOImpl implements IBankcardAO {

    @Autowired
    private IBankcardBO bankcardBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Override
    public String addBankcard(XN802020Req req) {
        // 验证短信验证码
        // if (StringUtils.isNotBlank(req.getSmsCaptcha())) {
        // smsOutBO.checkCaptcha(req.getBindMobile(), req.getSmsCaptcha(),
        // "802020", null);
        // }

        Bankcard data = new Bankcard();
        data.setSystemCode(req.getCompanyCode());
        data.setBankcardNumber(req.getBankcardNumber());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());

        data.setSubbranch(req.getSubbranch());
        data.setBindMobile(req.getBindMobile());
        data.setUserId(req.getUserId());
        data.setRealName(req.getRealName());
        data.setType(req.getType());

        data.setCurrency(req.getCurrency());
        data.setRemark(req.getRemark());
        return bankcardBO.saveBankcard(data);
    }

    @Override
    public void dropBankcard(String code) {
        if (!bankcardBO.isBankcardExist(code)) {
            throw new BizException("xn0000", "银行卡编号不存在");
        }
        bankcardBO.removeBankcard(code);
    }

    @Override
    public void editBankcard(XN802022Req req) {
        Bankcard bankcard = bankcardBO.getBankcard(req.getCode());
        if (!bankcard.getBankcardNumber().equals(req.getBankcardNumber())) { // 有修改就去判断是否唯一
            List<Bankcard> list = bankcardBO.queryBankcardList(
                bankcard.getUserId(), bankcard.getSystemCode());
            for (Bankcard card : list) {
                if (req.getBankcardNumber().equals(card.getBankcardNumber())) {
                    throw new BizException("xn0000", "银行卡号已存在");
                }
            }
        }
        Bankcard data = new Bankcard();
        data.setCode(req.getCode());
        // 户名有传就修改，不传不修改
        if (StringUtils.isBlank(req.getRealName())) {
            data.setRealName(bankcard.getRealName());
        } else {
            data.setRealName(req.getRealName());
        }
        data.setBankcardNumber(req.getBankcardNumber());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setSubbranch(req.getSubbranch());
        data.setBindMobile(req.getBindMobile());
        data.setStatus(req.getStatus());
        data.setRemark(req.getRemark());
        bankcardBO.refreshBankcard(data);
    }

    @Override
    public Paginable<Bankcard> queryBankcardPage(int start, int limit,
            Bankcard condition) {
        return bankcardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Bankcard> queryBankcardList(Bankcard condition) {
        return bankcardBO.queryBankcardList(condition);
    }

    @Override
    public Bankcard getBankcard(String code) {
        return bankcardBO.getBankcard(code);
    }
}
