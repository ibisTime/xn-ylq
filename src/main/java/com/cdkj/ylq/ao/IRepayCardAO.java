package com.cdkj.ylq.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.RepayCard;

//CHECK ��鲢��ע�� 
public interface IRepayCardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addRepayCard(String pict, String bankcardNumber,
            String bankCode, String bankName, String subbranch,
            String ownerName, String remark, String companyCode);

    public int dropRepayCard(String code);

    public int editRepayCard(String code, String pict, String bankNumber,
            String bankCode, String bankName, String subbranch, String ownerName);

    public Paginable<RepayCard> queryRepayCardPage(int start, int limit,
            RepayCard condition);

    public List<RepayCard> queryRepayCardList(RepayCard condition);

    public RepayCard getRepayCard(String code);

    public void settle(String code, BigDecimal amount, String updater);

    public void open(String code, String updater);

    public void close(String code, String updater);

    public RepayCard getOpenCard(String companyCode);
}
