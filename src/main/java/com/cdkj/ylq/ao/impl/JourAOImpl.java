package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IJourAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.IJourBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.domain.Jour;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EJourStatus;
import com.cdkj.ylq.enums.ESysUser;
import com.cdkj.ylq.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:16:58 
 * @history:
 */
@Service
public class JourAOImpl implements IJourAO {

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IBusinessManBO businessManBO;

    /*
     * 人工调账： 1、判断流水账是否平，平则更改订单状态，不平则更改产生红冲蓝补订单，而后更改订单状态
     */
    @Override
    @Transactional
    public void checkJour(String code, BigDecimal checkAmount,
            String checkUser, String checkNote, String systemCode) {
        Jour jour = jourBO.getJourNotException(code);
        if (null != jour) {
            doCheckJourNow(code, checkAmount, checkUser, checkNote, jour);// 现在流水对账
        }
    }

    private void doCheckJourNow(String code, BigDecimal checkAmount,
            String checkUser, String checkNote, Jour jour) {
        if (!EJourStatus.todoCheck.getCode().equals(jour.getStatus())) {
            throw new BizException("xn000000", "该流水<" + code + ">不处于待对账状态");
        }
        if (checkAmount.compareTo(BigDecimal.ZERO) != 0) {
            jourBO.doCheckJour(jour, EBoolean.NO, checkAmount, checkUser,
                checkNote);
        } else {
            jourBO.doCheckJour(jour, EBoolean.YES, checkAmount, checkUser,
                checkNote);
        }
    }

    @Override
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition) {
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }

        Paginable<Jour> page = jourBO.getPaginable(start, limit, condition);
        String realName = null;
        for (Jour jour : page.getList()) {
            if (ESysUser.SYS_USER.getCode().equals(jour.getUserId())) {
                jour.setRealName(ESysUser.SYS_USER.getValue());
            } else {
                BusinessMan man = businessManBO
                    .getBusinessMan(jour.getUserId());
                if (man.getRealName() != null) {
                    realName = man.getRealName();
                    jour.setRealName(realName);
                }
            }
        }
        return page;
    }

    @Override
    public List<Jour> queryJourList(Jour condition) {
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }
        List<Jour> jourList = jourBO.queryJourList(condition);
        List<Jour> result = new ArrayList<Jour>();
        result.addAll(jourList);
        return result;
    }

    @Override
    public Jour getJour(String code) {
        Jour jour = jourBO.getJour(code);

        return jour;
    }

    @Override
    public BigDecimal getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd) {
        return jourBO.getTotalAmount(bizType, channelType, accountNumber,
            dateStart, dateEnd);
    }
}
