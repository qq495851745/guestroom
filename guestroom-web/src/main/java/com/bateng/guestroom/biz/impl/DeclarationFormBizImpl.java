package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.dao.DeclarationFormDao;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("declarationFormBiz")
public class DeclarationFormBizImpl implements DeclarationFormBiz {

    @Autowired
    private DeclarationFormDao declarationFormDao;
    @Override
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm) {

        return declarationFormDao.findDeclarationFormByPage(pageVo,declarationForm);
    }

    public DeclarationFormDao getDeclarationFormDao() {
        return declarationFormDao;
    }

    public void setDeclarationFormDao(DeclarationFormDao declarationFormDao) {
        this.declarationFormDao = declarationFormDao;
    }
}
