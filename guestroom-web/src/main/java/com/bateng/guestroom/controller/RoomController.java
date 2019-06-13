package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.RoomBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.entity.*;
import org.csource.common.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/guestroom")
public class RoomController extends BaseController {

    private static Logger logger= LoggerFactory.getLogger(RoomController.class);
    @Autowired
    private RoomBiz roomBiz;

    //跳转首页
    @RequestMapping(value="/room/index")
    public String index(){
      return  "room/roomIndex";
    }

    //第一次跳转
    @RequestMapping(value = {"/room/roomLevel/show/{roomLevel.id}","/declarationForm/roomLevel/room/show/{roomLevel.id}"} ,method = {RequestMethod.GET})
    public String findRoomByRoomLevel1(@PathVariable(value = "roomLevel.id")  int id, Model model, PageVo<Room> pageVo, RoomAndRoomLevel roomAndRoomLevel,HttpServletRequest request){
      pageVo=roomBiz.findRoomByPage(pageVo,roomAndRoomLevel);
      model.addAttribute("pageVo",pageVo);
      model.addAttribute("roomAndRoomLevel",roomAndRoomLevel);
        if(request.getRequestURL().indexOf("declarationForm")!=-1)
            return "declarationForm/guest/declarationForm_add_lookup_room_show";
        else
            return  "room/room_show";
    }

    //第二次跳转
    @RequestMapping(value = {"/room/roomLevel/show","/declarationForm/roomLevel/room/show"} ,method = {RequestMethod.POST})
    public String findRoomByRoomLevel2(Model model, PageVo<Room> pageVo, RoomAndRoomLevel roomAndRoomLevel,HttpServletRequest request){
        pageVo=roomBiz.findRoomByPage(pageVo,roomAndRoomLevel);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("roomAndRoomLevel",roomAndRoomLevel);
        if(request.getRequestURL().indexOf("declarationForm")!=-1)
            return "declarationForm/guest/declarationForm_add_lookup_room_show";
        else
            return  "room/room_show";
    }

    //跳转到添加页面
    @RequestMapping(value = "/room/toAdd",method = RequestMethod.GET)
    public String toAdd(){
        return "room/room_add";
    }

    //跳转添加页面查询层级页面
    @RequestMapping(value = "/room/roomLevel/all",method = RequestMethod.GET)
    public String findRoomLeverForToAdd(){
        return "room/room_room_level_lookup";
    }

   //做添加操作
    @RequestMapping(value = "/room",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doAdd(@RequestParam("roomLevel.id")List<RoomAndRoomLevel> roomAndRoomLevels, Room room, HttpServletRequest request){
         //验证房间名不能为空
        Room room2 = roomBiz.getRoomByName(room.getName());
        if(room2!=null){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("statusCode",StatusCodeDWZ.ERROR);
            jsonObject.put("message","房号已经存在,请重新输入！");
            return jsonObject.toJSONString();
        }
        if(roomAndRoomLevels==null){
             //数据输入有误
             return  errorJsonMes("选择层级不能为空，请重选择");
         }
        List<RoomPhoto> roomPhotos=new ArrayList<RoomPhoto>();
        try {
            MultipartHttpServletRequest mrequest= (MultipartHttpServletRequest) request;
            List<MultipartFile> list=mrequest.getFiles("photo");

            for(MultipartFile file:list){
                if(file.getSize()==0)
                    continue;
               RoomPhoto roomPhoto=new RoomPhoto();
               String client= FastDFSClient.uploadFile(file.getInputStream(),file.getOriginalFilename());
               roomPhoto.setPath(client);
               roomPhoto.setExt(FastDFSClient.getFileExt(file.getOriginalFilename()));
               roomPhoto.setOrigName(file.getOriginalFilename());
               roomPhoto.setRoom(room);
               roomPhotos.add(roomPhoto);

            }
        } catch (Exception e) {
           logger.error("图片上传出错",e);
            return  errorJsonMes("图片上传出错");
        }
        room.setRoomAndRoomLevels(roomAndRoomLevels);
        room.setRoomPhotos(roomPhotos);
        room=roomBiz.addRoom(room);
         if(room==null)
             return  errorJsonMes("添加出错");

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("rel","roomBox");
        jsonObject.put("oldTabId","w_c");
        jsonObject.put("callbackType","closeCurrent");
      return jsonObject.toJSONString();
    }




  //做删除操作
    @RequestMapping(value = "/room/{id}",method = RequestMethod.DELETE,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doDelete(@PathVariable("id") int id){
      roomBiz.deleteRoomById(id);
      JSONObject jsonObject=new JSONObject();
      jsonObject.put("statusCode",StatusCodeDWZ.OK);
      jsonObject.put("rel","roomBox");
      return jsonObject.toJSONString();
    }


    //跳转修改页面
    @RequestMapping(value = "/room/toEdit/{id}",method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") int id,Model model){
        addurl(model);
       Room room=roomBiz.getRoomById(id);
       model.addAttribute("room",room);
        return  "room/room_edit";
    }

    //做修改操作
    @RequestMapping(value = "/room",method = RequestMethod.PUT,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doEdit(@RequestParam("roomLevel.id")List<RoomAndRoomLevel> roomAndRoomLevels, Room room,HttpServletRequest request){
        MultipartHttpServletRequest mrequest= (MultipartHttpServletRequest) request;
        List<MultipartFile> files=mrequest.getFiles("photo");
        List<RoomPhoto> roomPhotos=new ArrayList<RoomPhoto>();
         for(MultipartFile file:files){
             if(file.getSize()==0)
                 continue;
             try {
                 String id=FastDFSClient.uploadFile(file.getInputStream(),file.getOriginalFilename());
                 RoomPhoto roomPhoto=new RoomPhoto();
                 roomPhoto.setPath(id);
                 roomPhoto.setOrigName(file.getOriginalFilename());
                 roomPhoto.setExt(FastDFSClient.getFileExt(file.getOriginalFilename()));
                 roomPhoto.setRoom(room);
                 roomPhotos.add(roomPhoto);
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (MyException e) {
                 e.printStackTrace();
             }

         }
         room.setRoomPhotos(roomPhotos);
         room.setRoomAndRoomLevels(roomAndRoomLevels);

         roomBiz.updateRoom(room);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode",StatusCodeDWZ.OK);
        jsonObject.put("rel","roomBox");
        jsonObject.put("oldTabId","w_c");
        jsonObject.put("callbackType","closeCurrent");
        return  jsonObject.toJSONString();
    }

    public RoomBiz getRoomBiz() {
        return roomBiz;
    }

    public void setRoomBiz(RoomBiz roomBiz) {
        this.roomBiz = roomBiz;
    }



}
