package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.dto.req.XN623040Req;
import com.cdkj.ylq.dto.req.XN623041Req;
import com.cdkj.ylq.dto.req.XN623042Req;
import com.cdkj.ylq.dto.req.XN623043Req;
import com.cdkj.ylq.dto.res.XN623050Res;

public interface ICertificationAO {
    static final String DEFAULT_ORDER_COLUMN = "apply_datetime";

    // 提交个人基本信息
    public void submitInfoBasic(XN623040Req req);

    // 提交职业信息
    public void submitInfoOccupation(XN623041Req req);

    // 提交紧急联系人
    public void submitInfoContact(XN623042Req req);

    // 提交银行卡信息
    public void submitInfoBankcard(XN623043Req req);

    // 查询个人认证信息
    public XN623050Res getCertiInfo(String userId);

    public Paginable<Certification> queryCertificationPage(int start,
            int limit, Certification condition);

    public Certification getCertification(Long id);

}
