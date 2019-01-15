package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IRepayCardAO;
import com.cdkj.ylq.bo.IRepayCardBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.RepayCard;
import com.cdkj.ylq.enums.ERepayCardStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

//CHECK ��鲢��ע�� 
@Service
public class RepayCardAOImpl implements IRepayCardAO {

    @Autowired
    private IRepayCardBO repayCardBO;

    @Override
    public String addRepayCard(String pict, String bankcardNumber,
            String bankCode, String bankName, String subbranch,
            String ownerName, String remark, String companyCode) {
        return repayCardBO.saveRepayCard(pict, bankcardNumber, bankCode,
            bankName, subbranch, ownerName, remark, companyCode);
    }

    @Override
    public int editRepayCard(String code, String pict, String bankNumber,
            String bankCode, String bankName, String subbranch, String ownerName) {
        RepayCard data = repayCardBO.getRepayCard(code);
        if (!ERepayCardStatus.TOOPEN.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "收款账户已开启，无法更改");
        }
        data.setPict(pict);
        data.setBankcardNumber(bankNumber);
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setSubbranch(subbranch);
        data.setOwnerName(ownerName);
        return repayCardBO.refreshRepayCard(data);
    }

    @Override
    public int dropRepayCard(String code) {
        if (!repayCardBO.isRepayCardExist(code)) {
            throw new BizException("xn0000", "收款账号不存在");
        }
        return repayCardBO.removeRepayCard(code);
    }

    @Override
    public Paginable<RepayCard> queryRepayCardPage(int start, int limit,
            RepayCard condition) {
        return repayCardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<RepayCard> queryRepayCardList(RepayCard condition) {
        return repayCardBO.queryRepayCardList(condition);
    }

    @Override
    public RepayCard getRepayCard(String code) {
        return repayCardBO.getRepayCard(code);
    }

    @Override
    public void settle(String code, BigDecimal amount, String updater) {
        RepayCard card = repayCardBO.getRepayCard(code);
        if (ERepayCardStatus.TOOPEN.getCode().equals(card.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "账号未开启，无法结算");
        }
        repayCardBO.refreshAmount(card, amount.negate(), updater, "结算金额:"
                + amount.toString());
    }

    @Override
    public void open(String code, String updater) {
        RepayCard card = repayCardBO.getRepayCard(code);
        if (repayCardBO.getOpenCard(card.getCompanyCode()) != null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前有开启账号，请先关闭");
        }
        repayCardBO.refrehStatus(card, ERepayCardStatus.OPEN.getCode(),
            updater, "收款账号开启");
    }

    @Override
    public void close(String code, String updater) {
        RepayCard card = repayCardBO.getRepayCard(code);
        if (!ERepayCardStatus.OPEN.getCode().equals(card.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "该账号状态无法关闭");
        }
        repayCardBO.refrehStatus(card, ERepayCardStatus.CLOSE.getCode(),
            updater, "收款账号关闭");
    }

    @Override
    public RepayCard getOpenCard(String companyCode) {
        return repayCardBO.getOpenCard(companyCode);
    }
}
