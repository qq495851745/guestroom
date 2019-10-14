package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.RoomLevelBiz;
import com.bateng.guestroom.config.constant.AttachJsonTreeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ajax/roomLevel")
public class RoomLevelAjaxController extends BaseController {

    @Autowired
    private RoomLevelBiz roomLevelBiz;
    //查询所有层级
    @RequestMapping(value = "/findAll",method = {RequestMethod.POST})
    public String findAllByFlag(){
        return  roomLevelBiz.findAllByFlagAjax(1);
    }

    //提供房间设置查询层级
    @RequestMapping(value = "/findAllForRoom",method = RequestMethod.POST)
    public String findAllForRoom(){
        Map<String,String> map=new HashMap<String,String>();
        map.put("rel", AttachJsonTreeDWZ.RoomDWZ.ROOM_TREE_REL);
        map.put("href",AttachJsonTreeDWZ.RoomDWZ.ROOM_TREE_HREF);
        return roomLevelBiz.findAllRoomLevelAjax(1,map);
    }


    public RoomLevelBiz getRoomLevelBiz() {
        return roomLevelBiz;
    }

    public void setRoomLevelBiz(RoomLevelBiz roomLevelBiz) {
        this.roomLevelBiz = roomLevelBiz;
    }
}
