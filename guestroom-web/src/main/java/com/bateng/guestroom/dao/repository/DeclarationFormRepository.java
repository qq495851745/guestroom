package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;

import java.util.List;

public interface DeclarationFormRepository {
    /**
     * 做分页
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm);

    public List<DeclarationForm> findDeclarationForms(DeclarationForm declarationForm);

}
