package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.dao.UserDao;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userBiz")
public class UserBizImpl implements UserBiz {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(User user) {
        //return userDao.findByPasswordAndUsername(user.getPassword(),user.getUsername());
        return  userDao.findByPasswordAndUsernameAndFlag(user.getPassword(),user.getUsername(),1);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getOne(id);
    }


    @Override
    public PageVo<User> findUserByPage(PageVo<User> pageVo, User user) {
        return userDao.findUserByPage(pageVo,user);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
