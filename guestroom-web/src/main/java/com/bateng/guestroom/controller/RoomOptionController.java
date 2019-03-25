package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.RoomOptionBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.RoomOption;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.RoundingMode;
import java.util.Date;


@Controller
@RequestMapping(value = "/option")
public class RoomOptionController extends BaseController {


    @Autowired
    private RoomOptionBiz roomOptionBiz;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "roomOption/roomOptionIndex";
    }

    //跳转第一次登录
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable(name = "id") int id, Model model) {
        PageVo<RoomOption> page = roomOptionBiz.findByPageAndPid(1, 10, id);
        RoomOption roomOption = roomOptionBiz.getRoomOptionById(id);
        RoomOption roomOption1 = new RoomOption();
        roomOption1.setRoomOption(roomOption);
        model.addAttribute("pageVo", page);
        model.addAttribute("roomOption", roomOption1);

        return "roomOption/room_option_show";
    }

    @RequestMapping(value = "show", method = RequestMethod.POST)
    public String showByPage(PageVo pageVo, RoomOption roomOption, Model model) {
        //带查询条件的查询
        //PageVo<RoomOption> page=roomOptionBiz.findByPageAndPid(pageVo.getPageNum(),pageVo.getNumPerPage(),1);
        PageVo<RoomOption> page = roomOptionBiz.findByPageAndPid(pageVo, roomOption);
        model.addAttribute("pageVo", page);
        model.addAttribute("roomOption", roomOption);
        return "roomOption/room_option_show";
    }

    //跳转添加页面
    @RequestMapping(value = "toAdd/{pid}", method = RequestMethod.GET)
    public String toAdd(@PathVariable("pid") int pid, Model model) {
        model.addAttribute("pId", pid);
        return "roomOption/room_option_add";
    }

    @ResponseBody
    //做添加操作
    @RequestMapping(value = "doAdd", method = RequestMethod.POST)
    public String doAdd(RoomOption roomOption) {
        roomOption.setFlag(1);
        roomOption.setCreateDate(new Date());
        roomOption.setUpdateDate(new Date());
        roomOptionBiz.addRoomOption(roomOption);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("callbackType", "closeCurrent");
        jsonObject.put("statusCode", 200);
        jsonObject.put("rel", "jbsxBox");
        jsonObject.put("refresh", true);//刷新树形菜单
        jsonObject.put("option", "option");
        jsonObject.put("selectId", "option" + roomOption.getRoomOption().getId());
        jsonObject.put("url", "ajax/option/findAll");
        jsonObject.put("treeId", "btOptionTree");
        //return "{\"callbackType\":\"closeCurrent\",\"statusCode\":200,\"rel\":\"jbsxBox\"}";
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "del/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String doDelete(@PathVariable("id") int id) {
        boolean bool = roomOptionBiz.findRoomOptionBypId(id);
        JSONObject jsonObject=new JSONObject();
        if (bool) {

                  jsonObject.put("statusCode",300);
                  jsonObject.put("message","该项目下有子项，不能删除，请先删除子项");
                  return  jsonObject.toJSONString();
        }else{
               RoomOption roomOption=roomOptionBiz.getRoomOptionById(id);
               roomOptionBiz.deleteRoomOptionById(id);
                    jsonObject.put("statusCode",200);
                    jsonObject.put("rel","jbsxBox");
                    jsonObject.put("refresh", true);//刷新树形菜单
                    jsonObject.put("option", "option");
                    jsonObject.put("url", "ajax/option/findAll");
                    jsonObject.put("treeId", "btOptionTree");
                    jsonObject.put("selectId", "option" + roomOption.getRoomOption().getId());
            return jsonObject.toJSONString();
        }

    }

    //跳转编辑页
    @RequestMapping(value = "toEdit/{id}",method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") int id,Model model){
        RoomOption roomOption=roomOptionBiz.getRoomOptionById(id);
        model.addAttribute("roomOption",roomOption);
        return "roomOption/room_option_edit";
    }

    //做编辑
    @ResponseBody
    @RequestMapping(value = "doEdit",method = RequestMethod.POST)
    public String doEdit(RoomOption roomOption){
        roomOption.setUpdateDate(new Date());
        roomOption.setFlag(1);
        roomOptionBiz.addRoomOption(roomOption);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("callbackType", "closeCurrent");
        jsonObject.put("statusCode", 200);
        jsonObject.put("rel", "jbsxBox");
        return jsonObject.toJSONString();
    }

    public RoomOptionBiz getRoomOptionBiz() {
        return roomOptionBiz;
    }

    public void setRoomOptionBiz(RoomOptionBiz roomOptionBiz) {
        this.roomOptionBiz = roomOptionBiz;
    }
}
