package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.RoomPhotoBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.entity.RoomPhoto;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/guestroom")
public class RoomPhotoController extends Serializers.Base {

    @Autowired
    private RoomPhotoBiz roomPhotoBiz;
    //删除图片
    @RequestMapping(value = "/roomPhoto/{id}",method = RequestMethod.DELETE,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doDeletePhoto(@PathVariable("id") int id){
        RoomPhoto roomPhoto=roomPhotoBiz.getRoomPhotoById(id);
        FastDFSClient.deleteFile(roomPhoto.getPath());//删除图片
        roomPhotoBiz.deleteRoomPhotoById(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("message","删除成功");
        return  jsonObject.toJSONString();
    }

    public RoomPhotoBiz getRoomPhotoBiz() {
        return roomPhotoBiz;
    }

    public void setRoomPhotoBiz(RoomPhotoBiz roomPhotoBiz) {
        this.roomPhotoBiz = roomPhotoBiz;
    }
}
