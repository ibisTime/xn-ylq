package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.dto.req.XN802020Req;
import com.cdkj.ylq.dto.req.XN802022Req;

/**
 * 
 * @author: lei 
 * @since: 2018年9月11日 下午5:41:25 
 * @history:
 */
public interface IBankcardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addBankcard(XN802020Req req);

    public void dropBankcard(String code);

    public void editBankcard(XN802022Req req);

    public Paginable<Bankcard> queryBankcardPage(int start, int limit,
            Bankcard condition);

    public List<Bankcard> queryBankcardList(Bankcard condition);

    public Bankcard getBankcard(String code);

}
