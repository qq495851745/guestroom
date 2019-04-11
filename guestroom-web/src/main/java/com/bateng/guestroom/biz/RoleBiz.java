package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Role;

public interface RoleBiz {

    public void addRole(Role role);

    /**
     * 返回所有角色 json
     * @return
     */
    public  String findRoleAjax();
}
