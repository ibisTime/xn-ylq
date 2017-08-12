package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IApplyAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Apply;

@Service
public class ApplyAOImpl implements IApplyAO {

    @Autowired
    private IApplyBO applyBO;

    @Override
    public String submitApply(Apply data) {
        return applyBO.saveApply(data);
    }

    @Override
    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition) {
        return applyBO.getPaginable(start, limit, condition);
    }

    @Override
    public Apply getApply(String code) {
        return applyBO.getApply(code);
    }
}
