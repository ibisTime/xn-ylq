package com.cdkj.ylq.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IChannelBankBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.IChannelBankDAO;
import com.cdkj.ylq.domain.ChannelBank;
import com.cdkj.ylq.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月10日 下午8:31:09 
 * @history:
 */
@Component
public class ChannelBankBOImpl extends PaginableBOImpl<ChannelBank> implements
        IChannelBankBO {

    @Autowired
    private IChannelBankDAO channelBankDAO;

    @Override
    public boolean isChannelBankExist(Long id) {
        ChannelBank condition = new ChannelBank();
        condition.setId(id);
        if (channelBankDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int saveChannelBank(ChannelBank data) {
        return channelBankDAO.insert(data);
    }

    @Override
    public int removeChannelBank(Long id) {
        int count = 0;
        if (id != null) {
            ChannelBank data = new ChannelBank();
            data.setId(id);
            count = channelBankDAO.delete(data);
        }
        return count;
    }

    @Override
    public void refreshChannelBank(ChannelBank data, String bankCode,
            String bankName, String updater, String remark) {
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setRemark(remark);

        channelBankDAO.update(data);
    }

    @Override
    public List<ChannelBank> queryChannelBankList(ChannelBank condition) {
        return channelBankDAO.selectList(condition);
    }

    @Override
    public ChannelBank getChannelBank(Long id) {
        ChannelBank data = null;
        if (id != null) {
            ChannelBank condition = new ChannelBank();
            condition.setId(id);
            data = channelBankDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "渠道银行不存在");
            }
        }
        return data;
    }

    @Override
    public ChannelBank getChannelBank(String bankCode) {
        ChannelBank data = null;
        if (StringUtils.isNotBlank(bankCode)) {
            ChannelBank condition = new ChannelBank();
            condition.setBankCode(bankCode);
            List<ChannelBank> list = channelBankDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }
}
