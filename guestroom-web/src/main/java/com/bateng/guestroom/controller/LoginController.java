package com.bateng.guestroom.controller;


import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.model.WebConfig;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.ref.ReferenceQueue;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserBiz userBiz;

    //跳转登录页面
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        addurl(model);
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(User user, Model model, HttpServletRequest request){
        HttpSession session= request.getSession();
//        session.setMaxInactiveInterval(60*10);
        addurl(model);
        user=userBiz.checkUser(user);
        if(user==null)
            return "login";
        else {
            session.setAttribute("user",user);
            return "redirect:index";
        }
    }

    @RequestMapping(value = "index")
    public String toIndex(Model model){
        addurl(model);
        return  "index";
    }






    //退出操作
    @RequestMapping(value = "loginout",method = RequestMethod.GET)
    public String loginout(Model model,HttpSession session){
        addurl(model);
        session.invalidate();
        return "login";
    }


    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
}
