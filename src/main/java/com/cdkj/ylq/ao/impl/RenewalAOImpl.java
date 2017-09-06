package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IRenewalAO;
import com.cdkj.ylq.bo.IRenewalBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Renewal;

@Service
public class RenewalAOImpl implements IRenewalAO {

    @Autowired
    private IRenewalBO renewalBO;

    @Override
    public Paginable<Renewal> queryRenewalPage(int start, int limit,
            Renewal condition) {
        return renewalBO.getPaginable(start, limit, condition);
    }

    @Override
    public Renewal getRenewal(String code) {
        return renewalBO.getRenewal(code);
    }
}
