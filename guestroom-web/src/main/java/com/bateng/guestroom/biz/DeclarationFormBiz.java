package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;

public interface DeclarationFormBiz {
    /**
     * 分页查询
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo,DeclarationForm declarationForm);
}
