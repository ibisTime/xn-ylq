package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Noticer;
import com.cdkj.ylq.dto.req.XN623160Req;
import com.cdkj.ylq.dto.req.XN623162Req;

//CHECK ��鲢��ע�� 
public interface INoticerBO extends IPaginableBO<Noticer> {

    public boolean isNoticerExist(String code);

    public String saveNoticer(XN623160Req req);

    public int removeNoticer(String code);

    public int refreshNoticer(Noticer data, XN623162Req req);

    public List<Noticer> queryNoticerList(Noticer condition);

    public Noticer getNoticer(String code);

    public List<Noticer> queryNoticersNow(String type, String companyCode);

}
