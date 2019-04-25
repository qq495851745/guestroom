package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.AppointFormBiz;
import com.bateng.guestroom.dao.AppointFormDao;
import com.bateng.guestroom.dao.DeclarationFormDao;
import com.bateng.guestroom.entity.AppointForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("appointFormBiz")
public class AppointFormBizImpl implements AppointFormBiz {
    @Autowired
    private AppointFormDao appointFormDao;
    @Autowired
    private DeclarationFormDao declarationFormDao;
    @Override
    @Transactional
    public void saveAppointForm(AppointForm appointForm) {
        appointForm.setCreateDate(new Date());
        appointFormDao.save(appointForm);
        //declarationFormDao.updateDeclarationForm2(2,appointForm.getDeclarationForm().getId());//已读状态
        declarationFormDao.updateDeclarationForm(appointForm.getId(),appointForm.getDeclarationForm().getId());
    }

    @Override
    public List<AppointForm> findAppointFormsByDeclarationFormId(int id) {
        return appointFormDao.findAllByDeclarationFormId(id);
    }

    public AppointFormDao getAppointFormDao() {
        return appointFormDao;
    }

    public void setAppointFormDao(AppointFormDao appointFormDao) {
        this.appointFormDao = appointFormDao;
    }

    public DeclarationFormDao getDeclarationFormDao() {
        return declarationFormDao;
    }

    public void setDeclarationFormDao(DeclarationFormDao declarationFormDao) {
        this.declarationFormDao = declarationFormDao;
    }
}
