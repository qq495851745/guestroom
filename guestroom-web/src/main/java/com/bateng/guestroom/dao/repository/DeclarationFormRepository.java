package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;

public interface DeclarationFormRepository {
    /**
     * 做分页
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm);
}
