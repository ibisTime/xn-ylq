package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.dto.req.XN623040Req;
import com.cdkj.ylq.dto.res.XN623050Res;

public interface ICertificationAO {
    static final String DEFAULT_ORDER_COLUMN = "apply_datetime";

    // 提交个人基本信息
    public String submitBasicInfo(XN623040Req req);

    // 查询个人认证信息
    public XN623050Res getCertiInfo(String userId);

    public Paginable<Certification> queryCertificationPage(int start,
            int limit, Certification condition);

    public Certification getCertification(Long id);

}
