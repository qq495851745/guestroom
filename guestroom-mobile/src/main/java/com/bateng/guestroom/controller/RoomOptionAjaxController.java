package com.bateng.guestroom.controller;

import com.bateng.guestroom.biz.RoomOptionBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ajax/option")
public class RoomOptionAjaxController {

    @Autowired
    private RoomOptionBiz roomOptionBiz;


    /**
     * 获取报修类别 获取有子元素的对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findAll1",produces ="application/json;charset=utf-8" )
    public String findRoomOptions1(){
        return roomOptionBiz.findRoomOption1Ajax();
    }

    /**
     * 获取所有客房属性
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findAll",produces = "application/json;charset=utf-8")
    public String findRoomOptions(){
        return roomOptionBiz.findRoomOptionAjax();
    }




    public RoomOptionBiz getRoomOptionBiz() {
        return roomOptionBiz;
    }

    public void setRoomOptionBiz(RoomOptionBiz roomOptionBiz) {
        this.roomOptionBiz = roomOptionBiz;
    }
}
