package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.User;

public interface UserBiz {
    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);



    public User checkUser(User user);

    public User getUserById(int id);
}
