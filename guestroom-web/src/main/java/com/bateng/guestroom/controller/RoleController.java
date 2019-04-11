package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.RoleBiz;
import com.bateng.guestroom.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/guestroom")
public class RoleController {

    @Autowired
    private RoleBiz roleBiz;


    @RequestMapping(value = "/role/ajax",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAllAjax(){
        return roleBiz.findRoleAjax();
    }

    @RequestMapping(value = "/role",method = {RequestMethod.GET})
    public String findAll(Model model){
        List<Role> roles=roleBiz.findRole();
        model.addAttribute("roles",roles);
        return  "user/user_add_lookup_role";
    }


    public RoleBiz getRoleBiz() {
        return roleBiz;
    }

    public void setRoleBiz(RoleBiz roleBiz) {
        this.roleBiz = roleBiz;
    }
}
