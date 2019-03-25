package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.bateng.guestroom.biz.UserLevelBiz;
import com.bateng.guestroom.config.constant.AttachJsonTreeDWZ;
import com.bateng.guestroom.dao.UserLevelDao;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.UserLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userLevelBiz")
public class UserLevelBizImpl implements UserLevelBiz {

    @Autowired
    private UserLevelDao userLevelDao;

    @Override
    public String findAllUserLevelAjax() {
        List<UserLevel> userLevels = userLevelDao.findAll();
        return JSONObject.toJSONString(userLevels, new SerializeFilter[]{new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                if (s.equals("updateDate"))
                    return false;
                else if (s.equals("createDate"))
                    return false;
                else
                    return true;
            }
        },new NameFilter() {
            @Override
            public String process(Object o, String s, Object o1) {
                if("userLevel".equals(s))
                    return  "pid";
                return s;
            }
        },new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object o1) {
               if("pid".equals(s)){
                   if(o1==null){
                       return 0;
                   }else {
                       UserLevel ul= (UserLevel) o1;
                       return  ul.getId();
                   }
               }

                return o1;
            }
        },new AfterFilter() {
            @Override
            public void writeAfter(Object o) {
                writeKeyValue("href", AttachJsonTreeDWZ.UserLevelDWZ.USER_LEVEL_TREE_HREF);
                writeKeyValue("rel",AttachJsonTreeDWZ.UserLevelDWZ.USER_LEVEL_TREE_REL);
            }
        }}, SerializerFeature.DisableCircularReferenceDetect);
    }


    @Override
    public PageVo<UserLevel> findUserLevelByPage(PageVo<UserLevel> pageVo, UserLevel userLevel) {
        return userLevelDao.findUserLevelByPage(pageVo,userLevel);
    }

    @Override
    @Transactional
    public void saveUserLevel(UserLevel userLevel) {
        userLevelDao.save(userLevel);
    }

    @Override
    @Transactional
    public void deleteUserLevelById(int id) {
        userLevelDao.deleteById(id);
    }

    @Override
    public List<UserLevel> findSubUserLevelByid(int id) {

        return userLevelDao.findAllByUserLevelId(id);
    }

    @Override
    public UserLevel getUserLevelById(int id) {
        return userLevelDao.getOne(id);
    }

    @Override
    @Transactional
    public void updateUserLevel(UserLevel userLevel) {
        UserLevel u1=userLevelDao.getOne(userLevel.getId());
        userLevel.setCreateDate(u1.getCreateDate());
        userLevelDao.save(userLevel);
    }

    public UserLevelDao getUserLevelDao() {
        return userLevelDao;
    }

    public void setUserLevelDao(UserLevelDao userLevelDao) {
        this.userLevelDao = userLevelDao;
    }
}
