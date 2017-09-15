/**
 * @Title IAccountBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:15:49 
 * @version V1.0   
 */
package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.BaofooPay;
import com.cdkj.ylq.dto.res.XN002500Res;
import com.cdkj.ylq.dto.res.XN002501Res;
import com.cdkj.ylq.dto.res.XN002510Res;
import com.cdkj.ylq.enums.EBizType;
import com.cdkj.ylq.enums.ECurrency;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:15:49 
 * @history:
 */
public interface IAccountBO {

    // 根据用户编号和币种获取账户
    public Account getRemoteAccount(String userId, ECurrency currency);

    // 根据用户编号进行账户资金划转
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote, String refNo);

    public void doTransferAmountRemote(String fromUserId,
            ECurrency fromCurrency, String toUserId, ECurrency toCurrency,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String refNo);

    public XN002500Res doWeiXinPayRemote(String applyUser, String toUser,
            String payGroup, String refNo, EBizType bizType, String bizNote,
            Long amount);

    public XN002501Res doWeiXinH5PayRemote(String applyUser, String openId,
            String toUser, String payGroup, String refNo, EBizType bizType,
            String bizNote, Long amount);

    public XN002510Res doAlipayRemote(String applyUser, String toUser,
            String payGroup, String refNo, EBizType bizType, String bizNote,
            Long amount);

    public Bankcard getBankcard(String userId);

    // 宝付代付
    public void baofooPay(List<BaofooPay> baofooPayList);

    // 宝付代付结果查询
    public void baofooPayQuery(List<String> borrowCodeList);

    // 宝付代扣
    public boolean baofooWithhold(String bankCode, String accountNo,
            String idNo, String realName, String mobile, Long transAmount);

}
