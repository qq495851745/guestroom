package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/guestroom")
public class UserController extends BaseController {

    @Autowired
    private UserBiz userBiz;

    //跳转首页
    @RequestMapping(value = "/user/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(PageVo<User> pageVo, User user, Model model) {
        pageVo = userBiz.findUserByPage(pageVo, user);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("user", user);
        return "user/user_index";
    }

    //跳转查找用户层级
    @RequestMapping(value = "user/userlevel/tolookup", method = RequestMethod.GET)
    public String toUserLevelLookup() {
        return "user/user_lookup_user_level";
    }

    //跳转添加用户页面
    @RequestMapping(value = "/user/toAdd", method = RequestMethod.GET)
    public String toAdd() {
        return "user/user_add";
    }

    //做添加操作
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(User user) {
        JSONObject jsonObject = new JSONObject();
        //验证用户名是否存在
        List<User> users = userBiz.findUserByName(user);
        if (users.size() > 0) {//这个用户已经存在
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            jsonObject.put("message", "当前用户名已经存在，不能使用");
        } else {
            userBiz.addUser(user);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
            jsonObject.put("navTabId", "w_e");
            jsonObject.put("message", "用户添加成功");
        }
        return jsonObject.toJSONString();
    }

    //根据层级列出所有用户
    @RequestMapping(value = {"/appointForm/user/{userLevel.id}","/appointForm/user"},method = {RequestMethod.GET,RequestMethod.POST})
    public String list(@PathVariable(value = "userLevel.id",required = false)Integer id, User user,PageVo<User> pageVo,Model model){
        pageVo = userBiz.findUserByPage(pageVo,user);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("user",user);
        return "appointForm/user/appointForm_user_show";
    }

    //跳转查询UserLevel页面
    @RequestMapping(value = "/userLevel/lookup", method = {RequestMethod.GET})
    public String toLookupUserLevel() {
        return "user/user_add_lookup_userLevel";
    }

    //做删除用户
    @RequestMapping(value = "/user/{id}", method = {RequestMethod.DELETE},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delete(@PathVariable("id") int id) {
        userBiz.deleteUserById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message", "删除成功!");
        jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
        jsonObject.put("navTabId", "w_e");
        return jsonObject.toJSONString();
    }

    //跳转修改页面
    @RequestMapping(value = "/user/{id}",method = {RequestMethod.GET})
    public String toEdit(@PathVariable("id") int id,Model model){
        User user=userBiz.getUserById(id);
        model.addAttribute("user",user);
        return "user/user_edit";
    }

    //做修改
    @RequestMapping(value = "/user",method = RequestMethod.PUT,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doEdit(User user){
        userBiz.updateUser(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message", "修改成功!");
        jsonObject.put("navTabId", "w_e");
        return  jsonObject.toJSONString();
    }


    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
}
