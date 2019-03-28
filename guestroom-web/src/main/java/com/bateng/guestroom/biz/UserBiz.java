package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;

import java.util.List;

public interface UserBiz {
    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);



    public User checkUser(User user);

    public User getUserById(int id);


    public PageVo<User> findUserByPage(PageVo<User> pageVo,User user);
}
