package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.DeclarationFormStatusBiz;
import com.bateng.guestroom.config.constant.AttachJsonTreeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/guestroom")
public class DeclarationFormStatusController extends BaseController {
    @Autowired
    private DeclarationFormStatusBiz declarationFormStatusBiz;

    @RequestMapping(value = "/ajax/declarationFormStatus", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findDeclarationFormStatusAjax() {
        return declarationFormStatusBiz.findAllAjax();
    }

    @RequestMapping(value = "/declarationForm/declarationFormStatus/show", produces = "application/json;charset=utf-8")
    public String toDeclarationFormStatus() {
        return "declarationForm/guest/declarationFormStatus_lookup_show";
    }
}
