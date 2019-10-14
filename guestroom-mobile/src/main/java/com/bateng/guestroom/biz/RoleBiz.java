package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Role;

import java.util.List;

public interface RoleBiz {

    public void addRole(Role role);

    /**
     * 返回所有角色 json
     * @return
     */
    public  String findRoleAjax();

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> findRole();
}
