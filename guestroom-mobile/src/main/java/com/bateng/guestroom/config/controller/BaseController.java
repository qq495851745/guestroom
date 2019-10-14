package com.bateng.guestroom.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.interceptor.MD5PropertyEditor;
import com.bateng.guestroom.config.model.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

    @Autowired
    private WebConfig webConfig;


    //添加路径baseurl
    public void addurl(Model model){
        //System.out.println(webConfig.getUrl());
        model.addAttribute("url",webConfig.getUrl());
        model.addAttribute("photoUrl",webConfig.getPhotoUrl());
    }

    /**
     * 返回错误提示
     * @param error
     * @return
     */
    public String errorJsonMes(String error){
        //数据输入有误
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
        jsonObject.put("message",error);
        return  jsonObject.toJSONString();
    }

    public WebConfig getWebConfig() {
        return webConfig;
    }

    public void setWebConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
      binder.registerCustomEditor(String.class,"password",new MD5PropertyEditor());
    }
}
