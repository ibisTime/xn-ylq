package com.cdkj.ylq.bo;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Certification;

public interface ICertificationBO extends IPaginableBO<Certification> {

    public String saveCertification(Certification data);

    public Certification getCertification(Long id);

}
