/**
 * @Title IJourAO.java 
 * @Package com.std.account.ao 
 * @Description 
 * @author xieyj  
 * @date 2016年12月23日 下午9:05:07 
 * @version V1.0   
 */
package com.cdkj.ylq.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Jour;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:05:07 
 * @history:
 */
public interface IJourAO {

    String DEFAULT_ORDER_COLUMN = "code";

    // 流水对账(包括现在和历史流水)
    public void checkJour(String code, BigDecimal checkAmount,
            String checkUser, String checkNote, String systemCode);

    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition);

    public List<Jour> queryJourList(Jour condition);

    public Jour getJour(String code);

    public BigDecimal getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd);

    // 获取一段时间统计金额
    // public XN802901Res getTotalAmountByDate(String accountNumber,
    // String dateStart, String dateEnd);
}
