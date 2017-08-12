package com.cdkj.ylq.bo;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Apply;

public interface IApplyBO extends IPaginableBO<Apply> {

    public String saveApply(Apply data);

    public Apply getApply(String code);

}
