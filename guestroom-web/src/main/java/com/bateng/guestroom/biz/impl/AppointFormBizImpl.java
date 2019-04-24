package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.AppointFormBiz;
import com.bateng.guestroom.dao.AppointFormDao;
import com.bateng.guestroom.entity.AppointForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("appointFormBiz")
public class AppointFormBizImpl implements AppointFormBiz {
    @Autowired
    private AppointFormDao appointFormDao;
    @Override
    @Transactional
    public void saveAppointForm(AppointForm appointForm) {
        appointForm.setCreateDate(new Date());
        appointFormDao.save(appointForm);
    }

    public AppointFormDao getAppointFormDao() {
        return appointFormDao;
    }

    public void setAppointFormDao(AppointFormDao appointFormDao) {
        this.appointFormDao = appointFormDao;
    }
}
