package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IRepayCardBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IRepayCardDAO;
import com.cdkj.ylq.domain.RepayCard;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.ERepayCardStatus;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Component
public class RepayCardBOImpl extends PaginableBOImpl<RepayCard> implements
        IRepayCardBO {

    @Autowired
    private IRepayCardDAO repayCardDAO;

    @Override
    public boolean isRepayCardExist(String code) {
        RepayCard condition = new RepayCard();
        condition.setCode(code);
        if (repayCardDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveRepayCard(String pict, String bankcardNumber,
            String bankCode, String bankName, String subbranch,
            String ownerName, String remark, String companyCode) {
        String code = OrderNoGenerater.generateM(EGeneratePrefix.RepayCard
            .getCode());
        RepayCard data = new RepayCard();
        data.setCode(code);
        data.setPict(pict);
        data.setBankcardNumber(bankcardNumber);
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setSubbranch(subbranch);
        data.setOwnerName(ownerName);
        data.setAmount(BigDecimal.ZERO);
        data.setStatus(ERepayCardStatus.TOOPEN.getCode());
        data.setCreateDatetime(new Date());
        data.setRemark(remark);
        data.setCompanyCode(companyCode);
        repayCardDAO.insert(data);
        return code;
    }

    @Override
    public int removeRepayCard(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            RepayCard data = new RepayCard();
            data.setCode(code);
            count = repayCardDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshRepayCard(RepayCard data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = repayCardDAO.update(data);
        }
        return count;
    }

    @Override
    public List<RepayCard> queryRepayCardList(RepayCard condition) {
        return repayCardDAO.selectList(condition);
    }

    @Override
    public RepayCard getRepayCard(String code) {
        RepayCard data = null;
        if (StringUtils.isNotBlank(code)) {
            RepayCard condition = new RepayCard();
            condition.setCode(code);
            data = repayCardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "收款账号不存在");
            }
        }
        return data;
    }

    @Override
    public void refrehStatus(RepayCard data, String status, String updater,
            String remark) {
        if (null != data) {
            data.setStatus(status);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            repayCardDAO.updateStatus(data);
        }
    }

    @Override
    public void refreshAmount(RepayCard data, BigDecimal amount,
            String updater, String remark) {
        if (null != data) {
            data.setAmount(data.getAmount().add(amount));
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            repayCardDAO.updateAmount(data);
        }
    }

    @Override
    public RepayCard getOpenCard(String companyCode) {
        RepayCard condition = new RepayCard();
        condition.setCompanyCode(companyCode);
        condition.setStatus(ERepayCardStatus.OPEN.getCode());
        return repayCardDAO.select(condition);
    }
}
