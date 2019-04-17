package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.biz.RoomLevelBiz;
import com.bateng.guestroom.config.constant.AttachJsonTreeDWZ;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.DeclarationFormPhoto;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/guestroom")
public class DeclarationFormController {

    @Autowired
    private DeclarationFormBiz declarationFormBiz;
    @Autowired
    private RoomLevelBiz roomLevelBiz;
    @RequestMapping(value = "/declarationForm/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(PageVo<DeclarationForm> pageVo, Model model, DeclarationForm declarationForm, HttpSession session){
        User u= (User) session.getAttribute("user");

        declarationForm.setUser(u);//获取报修人
        //查询报修单
        pageVo=declarationFormBiz.findDeclarationFormByPage(pageVo,declarationForm);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("declarationForm",declarationForm);

        return "declarationForm/declarationForm_index";
    }



    //跳转添加页面
    @RequestMapping(value = "/declarationForm/toAdd",method = RequestMethod.GET)
    public String toAdd(){
        return "declarationForm/declarationForm_add";
    }


    //做添加操作
    @RequestMapping(value = "/declarationForm",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(DeclarationForm declarationForm, HttpSession session, @RequestParam("photo") MultipartFile[] photos) throws Exception{
        List<DeclarationFormPhoto> photoList =new ArrayList<DeclarationFormPhoto>();
        for(MultipartFile file:photos){//保存文件
            String orname=file.getOriginalFilename();//获取原始文件名
            if(orname.equals(""))
                continue;
            String ext=FastDFSClient.getFileExt(orname);//获取扩展名
            String path=FastDFSClient.uploadFile(file.getInputStream(),orname);//上传文件
            DeclarationFormPhoto photo=new DeclarationFormPhoto();
            photo.setCreateDate(new Date());
            photo.setExt(ext);
            photo.setPath(path);
            photo.setOrigName(orname);
            photo.setDeclarationForm(declarationForm);
            photoList.add(photo);//添加到列表
        }
        declarationForm.setDeclarationFormPhotos(photoList);
        User user= (User) session.getAttribute("user");
        declarationForm.setUser(user);
        declarationFormBiz.saveDeclarationForm(declarationForm);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
        jsonObject.put("navTabId", "w_14");
        jsonObject.put("message", "报修单添加成功");
        return jsonObject.toJSONString();
    }

    //添加报修单查询roomOption
    @RequestMapping(value = "/declarationForm/roomOption",method = RequestMethod.GET)
    public String toRoomOptionLookup(){
        return "declarationForm/declarationForm_add_lookup_roomOption";
    }


    //跳转添加报修单查询Room
    @RequestMapping(value = "/declarationForm/room",method = RequestMethod.GET)
    public String toRoom(){
        return "declarationForm/declarationForm_add_lookup_room_index";
    }

    //查询room
    @RequestMapping(value = "/declarationForm/room",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findRoom(){
        Map<String,String> map=new HashMap<String,String>();
        map.put("rel", AttachJsonTreeDWZ.RoomDWZ.DECLARATION_ROOM_TREE_REL);
        map.put("href",AttachJsonTreeDWZ.RoomDWZ.DECLARATION_ROOM_TREE_HREF);

        return roomLevelBiz.findAllRoomLevelAjax(1,map);
    }

    public DeclarationFormBiz getDeclarationFormBiz() {
        return declarationFormBiz;
    }

    public void setDeclarationFormBiz(DeclarationFormBiz declarationFormBiz) {
        this.declarationFormBiz = declarationFormBiz;
    }

    public RoomLevelBiz getRoomLevelBiz() {
        return roomLevelBiz;
    }

    public void setRoomLevelBiz(RoomLevelBiz roomLevelBiz) {
        this.roomLevelBiz = roomLevelBiz;
    }
}
