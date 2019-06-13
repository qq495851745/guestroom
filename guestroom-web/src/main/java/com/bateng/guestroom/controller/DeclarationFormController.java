package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.biz.RoomBiz;
import com.bateng.guestroom.biz.RoomLevelBiz;
import com.bateng.guestroom.biz.UserLevelBiz;
import com.bateng.guestroom.config.constant.AttachJsonTreeDWZ;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.interceptor.DatePropertyEditor;
import com.bateng.guestroom.config.interceptor.MD5PropertyEditor;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/guestroom")
public class DeclarationFormController  extends BaseController {

    @Autowired
    private DeclarationFormBiz declarationFormBiz;
    @Autowired
    private RoomLevelBiz roomLevelBiz;
    @Autowired
    private UserLevelBiz userLevelBiz;
    @Autowired
    private RoomBiz roomBiz;
    @RequestMapping(value = "/declarationForm/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(PageVo<DeclarationForm> pageVo, Model model, DeclarationForm declarationForm, HttpSession session){
        User u= (User) session.getAttribute("user");

        declarationForm.setUser(u);//获取报修人
        //查询报修单
        pageVo=declarationFormBiz.findDeclarationFormByPage(pageVo,declarationForm);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("declarationForm",declarationForm);

        return "declarationForm/guest/declarationForm_index";
    }



    //跳转添加页面
    @RequestMapping(value = "/declarationForm/toAdd",method = RequestMethod.GET)
    public String toAdd(){
        return "declarationForm/guest/declarationForm_add";
    }


    //做添加操作
    @RequestMapping(value = "/declarationForm",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(DeclarationForm declarationForm, HttpSession session, @RequestParam("photo") MultipartFile[] photos) throws Exception{
        //验证房号填写正确
        Room room=roomBiz.getRoomByName(declarationForm.getRoom().getName());
        if(room==null){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("statusCode",StatusCodeDWZ.ERROR);
            jsonObject.put("message","房号输入不正确！注意前后不要有空格！");
            return jsonObject.toJSONString();
        }else
            declarationForm.setRoom(room);
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
            //photo.setDeclarationForm(declarationForm);
            photoList.add(photo);//添加到列表
        }
        declarationForm.setDeclarationFormPhotos(photoList);
        User user= (User) session.getAttribute("user");
        declarationForm.setUser(user);
        DeclarationFormStatus declarationFormStatus=new DeclarationFormStatus();
        declarationFormStatus.setId(1);
        declarationForm.setDeclarationFormStatus(declarationFormStatus);
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
        return "declarationForm/guest/declarationForm_add_lookup_roomOption";
    }


    //跳转添加报修单查询Room
    @RequestMapping(value = "/declarationForm/room",method = RequestMethod.GET)
    public String toRoom(){
        return "declarationForm/guest/declarationForm_add_lookup_room_index";
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

    //做删除
    @RequestMapping(value = "/declarationForm/{id}",method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String del(@PathVariable("id") int id){
        JSONObject jsonObject=new JSONObject();
        DeclarationForm declarationForm = declarationFormBiz.getDeclarationFormById(id);
        if(declarationForm.getDeclarationFormStatus().getId() != 1){
            jsonObject.put("statusCode",StatusCodeDWZ.ERROR);
            jsonObject.put("message","工程已处理中，不能删除！");
            return  jsonObject.toJSONString();
        }
        declarationFormBiz.deleteById(id);

        jsonObject.put("statusCode",StatusCodeDWZ.OK);
        jsonObject.put("message","删除成功!");
        jsonObject.put("navTabId","w_14");
        return  jsonObject.toJSONString();
    }

    //跳转修改
    @RequestMapping(value = "/declarationForm/{id}",method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") int id,DeclarationForm declarationForm,Model model){
        declarationForm=declarationFormBiz.getDeclarationFormById(id);
        model.addAttribute("declarationForm",declarationForm);
        addurl(model);
        return  "declarationForm/guest/declarationForm_edit";
    }

    //做修改
    @RequestMapping(value = "/declarationForm",method = RequestMethod.PUT,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doEdit(DeclarationForm declarationForm,@RequestParam("photo") MultipartFile[] files) throws  Exception{
        JSONObject jsonObject=new JSONObject();
        DeclarationForm df=declarationFormBiz.getDeclarationFormById(declarationForm.getId());
        if(df.getDeclarationFormStatus().getId() != 1){
            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);
            //jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
            //jsonObject.put("navTabId", "w_14");
            jsonObject.put("message", "工程处理中！报修单不能修改");
            return jsonObject.toJSONString();
        }
        //保存图片
        List<DeclarationFormPhoto> photoList=new ArrayList<DeclarationFormPhoto>();
        for(MultipartFile file:files){
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
        declarationFormBiz.updateDeclarationForm(declarationForm);

        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("callbackType", "closeCurrent");//关闭当前标签页
        jsonObject.put("navTabId", "w_14");
        jsonObject.put("message", "报修单修改成功");
        return jsonObject.toJSONString();
    }

    //查询审核的报修单
    @RequestMapping(value = {"/declarationForm/status"},method = {RequestMethod.GET,RequestMethod.POST})
    public String lookup(PageVo<DeclarationForm> pageVo,DeclarationForm declarationForm,HttpSession session,Model model){
        //设置搜索条件，搜索待审核单子
        List<Integer> list=new ArrayList<Integer>();
        list.add(3);
        list.add(6);
        declarationForm.setDeclarationFormStatusList(list);
        declarationForm.setUser((User) session.getAttribute("user"));
        pageVo = declarationFormBiz.findDeclarationFormByPage(pageVo,declarationForm);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("declarationForm",declarationForm);
        return "repairForm/guest/declarationForm_index";
    }


    /**
     *
     * 以下方法是工程人员操作报修单
     *
     *
     * begin
     */

    /**
     * 工程查询新建状态报修单
     * @return
     */
    @RequestMapping(value = "project/declarationForm/show",method = {RequestMethod.GET,RequestMethod.POST})
    public String show(PageVo<DeclarationForm> pageVo,DeclarationForm declarationForm,Model model,HttpSession session){
        DeclarationFormStatus declarationFormStatus=new DeclarationFormStatus();
        declarationFormStatus.setId(1);
        declarationForm.setDeclarationFormStatus(declarationFormStatus);

        pageVo = declarationFormBiz.findDeclarationFormByPage(pageVo,declarationForm);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("declarationForm",declarationForm);
        User user= (User) session.getAttribute("user");
        try {
            model.addAttribute("flag",userLevelBiz.findAllUserLevelAjaxByPid(user.getUserLevel().getId()).equals("[]"));
        } catch (Exception e) {
           model.addAttribute("flag",true);
        }
        return "declarationForm/project/declarationForm_project_index";
    }


    //查询工程，待维修的单子
    @RequestMapping(value = "/project/appointForm",method = {RequestMethod.GET,RequestMethod.POST})
    public String list(PageVo<DeclarationForm> pageVo,DeclarationForm declarationForm,HttpSession session,Model model){
        AppointForm appointForm=new AppointForm();
        appointForm.setUser1((User) session.getAttribute("user"));
        declarationForm.setAppointForm(appointForm);//设置委派查询参数

        //设置参数
        List<Integer> list=new ArrayList<Integer>();
        list.add(2);
        list.add(5);
        declarationForm.setDeclarationFormStatusList(list);

        pageVo=declarationFormBiz.findDeclarationFormByPage(pageVo,declarationForm);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("flag",true);
        return "declarationForm/project/declarationForm_project_index";
    }



    /**
     * end
     *
     */

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

    public UserLevelBiz getUserLevelBiz() {
        return userLevelBiz;
    }

    public void setUserLevelBiz(UserLevelBiz userLevelBiz) {
        this.userLevelBiz = userLevelBiz;
    }

    public RoomBiz getRoomBiz() {
        return roomBiz;
    }

    public void setRoomBiz(RoomBiz roomBiz) {
        this.roomBiz = roomBiz;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,"finishDate",new DatePropertyEditor());
    }
}
