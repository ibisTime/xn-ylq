package com.cdkj.ylq.ao;

import java.math.BigDecimal;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.dto.res.XN623091Res;

public interface IBorrowOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 客户端：使用额度借款
    public String borrow(String userId, Long couponId, String productCode);

    // 管理端：审核
    public void doApprove(String code, String approveResult, String approver,
            String approveNote);

    // 管理端：线下放款
    public void doLoanOffline(String code, String result, String updater,
            String remark);

    // 客户端：打款失败后，我已修改银行卡，重新申请放款
    public void resubmitLoan(String code);

    // 客户端：我要还款
    public Object repay(String code);

    // 客户端：还款成功回调
    public void repaySuccess(String payGroup, String payType, String payCode,
            BigDecimal amount);

    // 正常分期
    public void nomalStaging(String ruleCode, String orderCode);

    // 逾期分期
    public void yqStaging(String ruleCode, String orderCode);

    // 管理端：催收（发送短信至紧急联系人和运营商联系人中排名前N的手机）
    public void cuishou(String code);

    // 管理端：确认坏账
    public void confirmBad(String code, String updater, String remark);

    // 管理端：归档
    public void archive(String code, String updater, String remark);

    // 管理端：借款订分页单查询
    public Paginable<BorrowOrder> queryBorrowPage(int start, int limit,
            BorrowOrder condition);

    // 客户端：我的借款订单查询
    public Paginable<BorrowOrder> queryMyBorrowPage(int start, int limit,
            BorrowOrder condition);

    public BorrowOrder getBorrow(String code);

    // 定时器调用：每日00:00:00点检查逾期情况
    public void doCheckOverdueDaily();

    // 定时器调用：每日17:00:00点检查明天即将到期借款，并短信提醒
    public void doCheckWillRepayDaily();

    // 查询用户当前是否有借款
    public XN623091Res isBorrowing(String userId);

    public void editRemark(String code, String remark);

}
