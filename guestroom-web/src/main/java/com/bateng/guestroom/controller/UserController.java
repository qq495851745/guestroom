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

    @RequestMapping(value = "/user/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(PageVo<User> pageVo, User user, Model model){
        pageVo=userBiz.findUserByPage(pageVo,user);
        model.addAttribute("pageVo",pageVo);
        return "user/user_index";
    }

    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
}
