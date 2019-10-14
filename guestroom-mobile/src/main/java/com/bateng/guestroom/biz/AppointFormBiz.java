package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.AppointForm;

import java.util.List;

public interface AppointFormBiz {
    /**
     * 添加
     * @param appointForm
     */
    public void saveAppointForm(AppointForm appointForm);

    public List<AppointForm> findAppointFormsByDeclarationFormId(int id);
}
