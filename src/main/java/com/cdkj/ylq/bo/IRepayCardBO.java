package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.RepayCard;

public interface IRepayCardBO extends IPaginableBO<RepayCard> {

    public boolean isRepayCardExist(String code);

    public String saveRepayCard(String pict, String bankcardNumber,
            String bankCode, String bankName, String subbranch,
            String ownerName, String remark, String companyCode);

    public int removeRepayCard(String code);

    public int refreshRepayCard(RepayCard data);

    public List<RepayCard> queryRepayCardList(RepayCard condition);

    public RepayCard getRepayCard(String code);

    public void refrehStatus(RepayCard data, String status, String updater,
            String remark);

    public void refreshAmount(RepayCard data, BigDecimal amount,
            String updater, String remark);

    public RepayCard getOpenCard(String companyCode);

}
