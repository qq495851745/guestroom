package com.bateng.guestroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guestroom")
public class AppointForm {

    //跳转添加委派单
    @RequestMapping(value = "/appointForm/declarationForm/{id}",method = RequestMethod.GET)
    public String add(@PathVariable("id") int id){
        return "";
    }
}
