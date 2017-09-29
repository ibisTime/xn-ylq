package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Contract;

public interface IContractDAO extends IBaseDAO<Contract> {
    String NAMESPACE = IContractDAO.class.getName().concat(".");

    public int stopContract(Contract data);
}
