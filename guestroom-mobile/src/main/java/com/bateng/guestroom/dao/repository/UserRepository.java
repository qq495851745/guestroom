package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;

import java.util.List;

public interface UserRepository {

    public PageVo<User> findUserByPage(PageVo<User> pageVo,User user);
}
