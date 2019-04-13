package com.bateng.guestroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guestroom")
public class DeclarationFormController {

    @RequestMapping(value = "/declarationForm/index",method = {RequestMethod.GET})
    public String index(){
        return "declarationForm/declarationForm_index";
    }
}
