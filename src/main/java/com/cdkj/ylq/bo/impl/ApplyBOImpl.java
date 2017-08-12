package com.cdkj.ylq.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.IApplyDAO;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.exception.BizException;

@Component
public class ApplyBOImpl extends PaginableBOImpl<Apply> implements IApplyBO {

    @Autowired
    private IApplyDAO applyDAO;

    @Override
    public String saveApply(Apply data) {
        String code = null;
        if (data != null) {
            applyDAO.insert(data);
        }
        return code;
    }

    @Override
    public Apply getApply(String code) {
        Apply data = null;
        if (StringUtils.isNotBlank(code)) {
            Apply condition = new Apply();
            condition.setCode(code);
            data = applyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "额度申请记录不存在");
            }
        }
        return data;
    }
}
