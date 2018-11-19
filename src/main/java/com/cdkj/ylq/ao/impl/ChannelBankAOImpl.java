package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IChannelBankAO;
import com.cdkj.ylq.bo.IChannelBankBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.ChannelBank;
import com.cdkj.ylq.dto.req.XN802110Req;
import com.cdkj.ylq.exception.BizException;

@Service
public class ChannelBankAOImpl implements IChannelBankAO {

    @Autowired
    private IChannelBankBO channelBankBO;

    @Override
    public int addChannelBank(XN802110Req req) {
        ChannelBank data = new ChannelBank();
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setChannelType(req.getChannelType());
        data.setChannelBank(req.getChannelbank());
        data.setDayAmount(StringValidater.toLong(req.getDayAmount()));
        data.setMonthAmount(StringValidater.toLong(req.getMonthAmount()));
        data.setOrderAmount(StringValidater.toLong(req.getOrderAmount()));
        data.setMaxOrder(StringValidater.toLong(req.getMaxOrder()));
        data.setRemark(req.getRemark());
        data.setStatus(req.getStatus());
        return channelBankBO.saveChannelBank(data);
    }

    @Override
    public void editChannelBank(String id, String bankCode, String bankName,
            String updater, String remark) {
        ChannelBank data = channelBankBO.getChannelBank(StringValidater
            .toLong(id));
        channelBankBO.refreshChannelBank(data, bankCode, bankName, updater,
            remark);
    }

    @Override
    public void dropChannelBank(Long id) {
        if (!channelBankBO.isChannelBankExist(id)) {
            throw new BizException("xn0000", "渠道银行序号不存在");
        }
        channelBankBO.removeChannelBank(id);
    }

    @Override
    public Paginable<ChannelBank> queryChannelBankPage(int start, int limit,
            ChannelBank condition) {
        Paginable<ChannelBank> page = channelBankBO.getPaginable(start, limit,
            condition);

        return page;
    }

    @Override
    public List<ChannelBank> queryChannelBankList(ChannelBank condition) {
        List<ChannelBank> list = channelBankBO.queryChannelBankList(condition);

        return list;
    }

    @Override
    public ChannelBank getChannelBank(Long id) {
        ChannelBank data = channelBankBO.getChannelBank(id);
        return data;
    }
}
