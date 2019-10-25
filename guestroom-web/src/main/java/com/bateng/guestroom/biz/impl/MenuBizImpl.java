package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.bateng.guestroom.biz.MenuBiz;
import com.bateng.guestroom.dao.MenuDao;
import com.bateng.guestroom.dao.UserDao;
import com.bateng.guestroom.entity.Menu;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("menuBiz")
public class MenuBizImpl implements MenuBiz {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Menu getMenuById(int id) {

        return menuDao.getOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMenu(Menu menu) {
        menuDao.save(menu);
    }

    @Override
    public List<Menu> findMenus() {
        Menu menu = new Menu();
        menu.setFlag(1);
        Example<Menu> ex = Example.of(menu);
        return menuDao.findAll(ex);
//        return menuDao.findAllByFlag1();
    }

    @Override
    public String findMenusByUserIdAjax(int id) {
        User user = userDao.getOne(id);
        user.getRole().getMenus().removeIf((t) -> t.getFlag()==2);
        return JSONObject.toJSONString(user.getRole().getMenus(), new SerializeFilter[]{
                new PropertyFilter() {
                    @Override
                    public boolean apply(Object o, String s, Object o1) {
                        if (s.equals("menus"))
                            return false;
                        else if (s.equals("flag"))
                            return false;
                        else if (s.equals("createDate"))
                            return false;
                        else if (s.equals("updateDate"))
                            return false;
                        else if (s.equals("roles"))
                            return false;
                        else
                            return true;
                    }
                },
                new NameFilter() {
                    @Override
                    public String process(Object o, String s, Object o1) {
                        if (s.equals("id"))
                            return "id";
                        else if (s.equals("menu"))
                            return "pId";
                        else if (s.equals("name"))
                            return "name";
                        else
                            return s;
                    }
                }, new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object o1) {
                if (s.equals("pId"))
                    if (o1 != null)
                        return ((Menu) o1).getId();
                    else
                        return 0;
                return o1;
            }
        }
        }, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String findMenusAjax() {
        List<Menu> menus = findMenus();

        return JSONObject.toJSONString(menus, new SerializeFilter[]{
                new PropertyFilter() {
                    @Override
                    public boolean apply(Object o, String s, Object o1) {
                        if (s.equals("menus"))
                            return false;
                        else if (s.equals("flag"))
                            return false;
                        else if (s.equals("createDate"))
                            return false;
                        else if (s.equals("updateDate"))
                            return false;
                        else
                            return true;
                    }
                },
                new NameFilter() {
                    @Override
                    public String process(Object o, String s, Object o1) {
                        if (s.equals("id"))
                            return "id";
                        else if (s.equals("menu"))
                            return "pId";
                        else if (s.equals("name"))
                            return "name";
                        else
                            return s;
                    }
                }, new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object o1) {
                if (s.equals("pId"))
                    if (o1 != null)
                        return ((Menu) o1).getId();
                    else
                        return 0;
                return o1;
            }
        }
        }, SerializerFeature.DisableCircularReferenceDetect);
    }

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
