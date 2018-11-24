package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IStagingBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IStagingDAO;
import com.cdkj.ylq.domain.Staging;
import com.cdkj.ylq.enums.EStagingStatus;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Component
public class StagingBOImpl extends PaginableBOImpl<Staging> implements
        IStagingBO {

    @Autowired
    private IStagingDAO stagingDAO;

    @Override
    public boolean isStagingExist(String code) {
        Staging condition = new Staging();
        condition.setCode(code);
        if (stagingDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Staging> queryStagingList(Staging condition) {
        return stagingDAO.selectList(condition);
    }

    @Override
    public Staging getStaging(String code) {
        Staging data = null;
        if (StringUtils.isNotBlank(code)) {
            Staging condition = new Staging();
            condition.setCode(code);
            data = stagingDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "分期记录不存在");
            }
        }
        return data;
    }

    @Override
    public String saveStaging(String applyUser, String orderCode,
            BigDecimal payAmount, Date lastPayDate, Integer count,
            Integer batch, String companyCode) {
        Staging data = new Staging();
        String code = OrderNoGenerater.generateM("ST");
        data.setCode(code);
        data.setApplyUser(applyUser);
        data.setOrderCode(orderCode);
        data.setPayAmount(payAmount);
        data.setLastPayDate(lastPayDate);
        data.setStatus(EStagingStatus.TOREPAY.getCode());
        data.setCount(count);
        data.setBatch(batch);
        data.setCompanyCode(companyCode);
        stagingDAO.insert(data);
        return code;
    }

    @Override
    public void refreshRepay(String code, String payType, String payCode,
            String payGroup) {
        Staging staging = getStaging(payCode);
        staging.setPayCode(payCode);
        staging.setPayType(payType);
        staging.setPayGroup(payGroup);
        staging.setPayDatetime(new Date());
    }
}
