package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.dao.UserDao;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;


@Service("userBiz")
public class UserBizImpl implements UserBiz {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(User user) {
        //return userDao.findByPasswordAndUsername(user.getPassword(),user.getUsername());
        return  userDao.findByPasswordAndUsernameAndFlag(user.getPassword(),user.getUsername(),0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        user.setUpdateDate(new Date());
        user.setCreateDate(new Date());
        userDao.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getOne(id);
    }


    @Override
    public PageVo<User> findUserByPage(PageVo<User> pageVo, User user) {
        return userDao.findUserByPage(pageVo,user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setUpdateDate(new Date());
        String m=null;
        try {
            m=DigestUtils.md5DigestAsHex("".getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(user.getPassword().equals(m)){//用户密码不修改
            userDao.updateUser(user.getRole().getId(),user.getUserLevel().getId(),user.getUpdateDate(),user.getId());
        }else
            userDao.updateUser(user.getRole().getId(),user.getUserLevel().getId(),user.getUpdateDate(),user.getId(),user.getPassword());
    }

    @Override
    public List<User> findUserByName(User user) {
        return userDao.findAllByUsername(user.getUsername());
    }

    @Override
    public boolean checkUserByUserLevel(int id) {
        List<User> list=userDao.findByUserLevelId(id);
        return list.size() != 0;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
