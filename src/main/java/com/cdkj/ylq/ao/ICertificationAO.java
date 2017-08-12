package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Certification;

public interface ICertificationAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCertification(Certification data);

    public Paginable<Certification> queryCertificationPage(int start,
            int limit, Certification condition);

    public Certification getCertification(Long id);

}
