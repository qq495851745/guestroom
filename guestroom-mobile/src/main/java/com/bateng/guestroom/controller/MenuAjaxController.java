package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.bateng.guestroom.biz.MenuBiz;
import com.bateng.guestroom.entity.Menu;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/ajax/menu")
public class MenuAjaxController {
     @Autowired
     private MenuBiz menuBiz;


     @RequestMapping(value = "findMenus" ,method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
     @ResponseBody
     public String findMenus(HttpSession session){
         User user= (User) session.getAttribute("user");
        return menuBiz.findMenusByUserIdAjax(user.getId());
         //return menuBiz.findMenusAjax();
     }

    public MenuBiz getMenuBiz() {
        return menuBiz;
    }

    public void setMenuBiz(MenuBiz menuBiz) {
        this.menuBiz = menuBiz;
    }
}
