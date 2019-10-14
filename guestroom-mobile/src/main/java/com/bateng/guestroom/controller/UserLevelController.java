package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.UserBiz;
import com.bateng.guestroom.biz.UserLevelBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.UserLevel;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/guestroom")
public class UserLevelController {

    @Autowired
    private UserLevelBiz userLevelBiz;
    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/userLevel/index",method = RequestMethod.GET)
    public String index(){

        return  "userLevel/userLevelIndex";
    }


    @RequestMapping(value = "/ajax/userLevel",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findUserLevel(){
        return  userLevelBiz.findAllUserLevelAjax();
    }

    //跳转显示页面
    @RequestMapping(value = "/userLevel/show/{userLevel.id}",method = {RequestMethod.GET,RequestMethod.POST})
    public String show(@PathVariable(value = "userLevel.id",required = false) int pid, PageVo<UserLevel> pageVo, UserLevel userLevel, Model model){
        model.addAttribute("pageVo",userLevelBiz.findUserLevelByPage(pageVo,userLevel));
        model.addAttribute("userLevel",userLevel);
        return  "userLevel/user_level_show";
    }

    //跳转添加页面
    @RequestMapping(value = "/userLevel/toAdd/{userLevel.id}",method = RequestMethod.GET)
    public String toAdd(UserLevel userLevel, Model model){
        model.addAttribute("userLevel",userLevel);
        return  "userLevel/user_level_add";
    }

    //做添加
    @RequestMapping(value = "/userLevel",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doAdd(UserLevel userLevel){
        userLevelBiz.saveUserLevel(userLevel);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("refresh",true);
        jsonObject.put("option","userLevel");
        jsonObject.put("treeId","btUserLevelTree");
        jsonObject.put("url","guestroom/ajax/userLevel");
        jsonObject.put("selectId","userLevel"+userLevel.getUserLevel().getId());
        jsonObject.put("callbackType","closeCurrent");
        jsonObject.put("rel","userLevelBox");
        return jsonObject.toJSONString();
    }


    //做删除操作
    @RequestMapping(value = "/userLevel/{id}",method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doDelete(UserLevel userLevel){
        boolean bool=userBiz.checkUserByUserLevel(userLevel.getId());
        if(bool){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("message","该项目被引用了，不能删除");
            jsonObject.put("statusCode",StatusCodeDWZ.ERROR);
            return  jsonObject.toJSONString();
        }
        userLevel=userLevelBiz.getUserLevelById(userLevel.getId());
        List<UserLevel> userLevels=userLevelBiz.findSubUserLevelByid(userLevel.getId());
        JSONObject jsonObject=new JSONObject();
        if(userLevels.size()!=0){
            jsonObject.put("message","删除不成功！请先删除子项目");
            jsonObject.put("statusCode",StatusCodeDWZ.ERROR);
        }else{
            jsonObject.put("message","删除成功!");
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("refresh",true);
            jsonObject.put("option","userLevel");
            jsonObject.put("treeId","btUserLevelTree");
            jsonObject.put("url","guestroom/ajax/userLevel");
            jsonObject.put("selectId","userLevel"+userLevel.getUserLevel().getId());
            jsonObject.put("rel","userLevelBox");
            userLevelBiz.deleteUserLevelById(userLevel.getId());

        }
        return jsonObject.toJSONString();
    }

    //跳转编辑页面
    @RequestMapping(value = "userLevel/toEdit/{id}",method = RequestMethod.GET)
    public String toEdit(UserLevel userLevel,Model model){
        model.addAttribute("userLevel",userLevelBiz.getUserLevelById(userLevel.getId()));
        return "userLevel/user_level_edit";
    }


    //做更新
    @RequestMapping(value = "/userLevel",method = RequestMethod.PUT,produces = "application/json;charset-utf-8")
    @ResponseBody
    public String doEdit(UserLevel userLevel){
        userLevelBiz.updateUserLevel(userLevel);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message","更新成功！");
        jsonObject.put("callbackType","closeCurrent");
        jsonObject.put("rel","userLevelBox");
        jsonObject.put("refresh",true);
        jsonObject.put("option","userLevel");
        jsonObject.put("treeId","btUserLevelTree");
        jsonObject.put("url","guestroom/ajax/userLevel");
        jsonObject.put("selectId","userLevel"+userLevel.getUserLevel().getId());
        return jsonObject.toJSONString();
    }


//获取所有UserLevel
    @RequestMapping(value = "/userLevel",method = {RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAll(){
        return  userLevelBiz.findAllUserLevelAjax();
    }



    public UserLevelBiz getUserLevelBiz() {
        return userLevelBiz;
    }

    public void setUserLevelBiz(UserLevelBiz userLevelBiz) {
        this.userLevelBiz = userLevelBiz;
    }

    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
}
