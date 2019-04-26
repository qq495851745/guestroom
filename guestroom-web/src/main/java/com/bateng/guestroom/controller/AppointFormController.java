package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.AppointFormBiz;
import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.biz.UserLevelBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.entity.AppointForm;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.DeclarationFormStatus;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/guestroom")
public class AppointFormController extends BaseController {

    @Autowired
    private DeclarationFormBiz declarationFormBiz;

    @Autowired
    private UserLevelBiz userLevelBiz;

    @Autowired
    private AppointFormBiz appointFormBiz;

    //跳转添加委派单
    @RequestMapping(value = "/appointForm/declarationForm/{id}",method = RequestMethod.GET)
    public String toAdd(@PathVariable("id") int id, Model model, DeclarationForm declarationForm){
        declarationForm = declarationFormBiz.getDeclarationFormById(id);
        model.addAttribute("declarationForm",declarationForm);
        //修改状态为已读
       /* DeclarationFormStatus declarationFormStatus=new DeclarationFormStatus();
        declarationFormStatus.setId(2);
        declarationForm.setDeclarationFormStatus(declarationFormStatus);
        declarationFormBiz.updateStatus(declarationForm);*/
        addurl(model);

        return "appointForm/appointForm_add";
    }

    //添加委派单
    @RequestMapping(value = "/appointForm",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(AppointForm appointForm,HttpSession session){
        User user2= (User) session.getAttribute("user");
        appointForm.setUser2(user2);

        appointFormBiz.saveAppointForm(appointForm);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("callbackType","closeCurrent");
        jsonObject.put("message","委派成功！");
        jsonObject.put("navTabId","w_15");
        return jsonObject.toJSONString();
    }

    //跳转用户查询页面
    @RequestMapping(value = "/declarationFrom/appointForm/user",method = RequestMethod.GET)
    public String tolookUser(){
        return  "appointForm/user/appointForm_user_index";
    }

    //获取当前用户的用户层级以下的用户
    @RequestMapping(value = "/declarationForm/appointForm/user",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String lookUser(HttpSession session){
        User user= (User) session.getAttribute("user");

        return userLevelBiz.findAllUserLevelAjaxByPid(user.getUserLevel().getId());
    }





    public DeclarationFormBiz getDeclarationFormBiz() {
        return declarationFormBiz;
    }

    public void setDeclarationFormBiz(DeclarationFormBiz declarationFormBiz) {
        this.declarationFormBiz = declarationFormBiz;
    }

    public UserLevelBiz getUserLevelBiz() {
        return userLevelBiz;
    }

    public void setUserLevelBiz(UserLevelBiz userLevelBiz) {
        this.userLevelBiz = userLevelBiz;
    }

    public AppointFormBiz getAppointFormBiz() {
        return appointFormBiz;
    }

    public void setAppointFormBiz(AppointFormBiz appointFormBiz) {
        this.appointFormBiz = appointFormBiz;
    }
}
