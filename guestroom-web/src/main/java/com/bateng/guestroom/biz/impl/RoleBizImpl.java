package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.RoleBiz;
import com.bateng.guestroom.dao.RoleDao;
import com.bateng.guestroom.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("roleBiz")
public class RoleBizImpl implements RoleBiz {
    @Autowired
    private RoleDao roleDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role) {
        roleDao.save(role);
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
