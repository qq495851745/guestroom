package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guestroom")
public class UserController {

    @Autowired
   private UserBiz userBiz;

    //跳转首页
    @RequestMapping(value = "/user/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(PageVo<User> pageVo, User user, Model model){
        pageVo=userBiz.findUserByPage(pageVo,user);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("user",user);
        return "user/user_index";
    }

    //跳转查找用户层级
    @RequestMapping(value = "user/userlevel/tolookup",method = RequestMethod.GET)
    public String toUserLevelLookup(){
        return "user/user_lookup_user_level";
    }

    //跳转添加用户页面
    @RequestMapping(value = "/user/toAdd",method = RequestMethod.GET)
    public String toAdd(){
        return  "user/user_add";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String add(User user){
        return "";
    }


    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
}
