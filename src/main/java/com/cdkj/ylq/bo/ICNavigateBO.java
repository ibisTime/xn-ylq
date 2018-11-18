package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.CNavigate;

public interface ICNavigateBO extends IPaginableBO<CNavigate> {

    public boolean isCNavigateExist(String code);

    public String saveCNavigate(CNavigate data);

    public int removeCNavigate(String code);

    public int refreshCNavigate(CNavigate data);

    public List<CNavigate> queryCNavigateList(CNavigate condition);

    public CNavigate getCNavigate(String code);

}
