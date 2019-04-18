package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.biz.DeclarationFormPhotoBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/guestroom")
public class DeclarationFormPhotoController {

    @Autowired
    private DeclarationFormPhotoBiz declarationFormPhotoBiz;
    @RequestMapping(value = "/declarationFormPhoto/{id}",method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delete(@PathVariable("id") int id){
        declarationFormPhotoBiz.deleteById(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message","图片删除成功");
        return  jsonObject.toJSONString();
    }

    public DeclarationFormPhotoBiz getDeclarationFormPhotoBiz() {
        return declarationFormPhotoBiz;
    }

    public void setDeclarationFormPhotoBiz(DeclarationFormPhotoBiz declarationFormPhotoBiz) {
        this.declarationFormPhotoBiz = declarationFormPhotoBiz;
    }
}

