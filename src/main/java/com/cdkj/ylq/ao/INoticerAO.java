package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Noticer;
import com.cdkj.ylq.dto.req.XN623160Req;
import com.cdkj.ylq.dto.req.XN623162Req;

public interface INoticerAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addNoticer(XN623160Req req);

    public int dropNoticer(String code);

    public int editNoticer(XN623162Req req);

    public Paginable<Noticer> queryNoticerPage(int start, int limit,
            Noticer condition);

    public List<Noticer> queryNoticerList(Noticer condition);

    public Noticer getNoticer(String code);

}
