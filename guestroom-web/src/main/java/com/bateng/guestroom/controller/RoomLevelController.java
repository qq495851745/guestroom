package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.RoomLevelBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.RoomLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/guestroom")  //根路径
public class RoomLevelController extends BaseController {

    @Autowired
    private RoomLevelBiz roomLevelBiz;

    //跳转到roomlevel首页
    @RequestMapping(value = "roomLevel/index", method = RequestMethod.GET)
    public String toIndex() {
        return "roomLevel/room_level_index";
    }

    //跳转到roomlevel查询所有数据  第一次进查询
    @RequestMapping(value = "roomlevel/show/{pId}", method = RequestMethod.GET)
    public String firstFind(@PathVariable("pId") int pId, Model model) {
        PageVo<RoomLevel> pageVo = new PageVo<RoomLevel>();
        pageVo.setPageNum(1);
        pageVo.setNumPerPage(10);
        RoomLevel roomLevel = new RoomLevel();//查询条件
        roomLevel.setRoomLevel(new RoomLevel(pId));
        roomLevel.setFlag(1);
        pageVo = roomLevelBiz.findALLByPageAndCon(pageVo, roomLevel);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("roomLevel", roomLevel);
        /**
         * 1.第一进来
         * 2.查询所有
         * 3.带条件
         * 4.带页码
         *
         */
        return "roomLevel/room_level_show";
    }


    //第二次进
    @RequestMapping(value = "roomLevel/show", method = RequestMethod.POST)
    public String show(PageVo<RoomLevel> pageVo, RoomLevel roomLevel, Model model) {
        pageVo = roomLevelBiz.findALLByPageAndCon(pageVo, roomLevel);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("roomLevel", roomLevel);
        return "roomLevel/room_level_show";
    }


    //跳转到添加页面
    @RequestMapping(value = "roomLevel/toAdd/{pId}", method = RequestMethod.GET)
    public String toAdd(@PathVariable("pId") int pId, Model model) {
        model.addAttribute("pId", pId);
        return "roomLevel/room_level_add";
    }

    //做添加操作
    @RequestMapping(value = "roomLevel/doAdd", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doAdd(RoomLevel roomLevel) {
        roomLevelBiz.saveRoomLevel(roomLevel);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("rel", "roomLevelBox");
        jsonObject.put("callbackType", "closeCurrent");
        jsonObject.put("refresh", true);//刷新树形菜单
        jsonObject.put("option", "roomLevel");
        jsonObject.put("treeId", "btroomLevelTree");
        jsonObject.put("url", "ajax/roomLevel/findAll");
        jsonObject.put("selectId", "roomLevel" + roomLevel.getRoomLevel().getId());
        return jsonObject.toJSONString();
    }

    //删除操作
    @RequestMapping(value = "roomLevel/del/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doDel(@PathVariable int id) {
        //有子元素不能删除
        boolean bool = roomLevelBiz.isFindChild(id);
        JSONObject jsonObject = new JSONObject();
        if (bool) {//有子元素

            jsonObject.put("statusCode", StatusCodeDWZ.ERROR);//错误 删除不成功
            jsonObject.put("message", "选中项下有子项目，不能删除！");
        } else {//没有子元素  可以做删除操作
            RoomLevel roomLevel = roomLevelBiz.getRoomLevelById(id);
            roomLevelBiz.deleteRoomLevelById(id);
            jsonObject.put("statusCode", StatusCodeDWZ.OK);
            jsonObject.put("message", "删除成功！");
            jsonObject.put("rel", "roomLevelBox");
            /* jsonObject.put("callbackType", "closeCurrent");*/
            jsonObject.put("refresh", true);//刷新树形菜单
            jsonObject.put("option", "roomLevel");
            jsonObject.put("treeId", "btroomLevelTree");
            jsonObject.put("url", "ajax/roomLevel/findAll");
            jsonObject.put("selectId", "roomLevel" + roomLevel.getRoomLevel().getId());
        }
        return jsonObject.toJSONString();
    }

    //跳转到修改页面
    @RequestMapping(value = "roomLevel/toEdit/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable("id") int id, Model model) {
        RoomLevel roomLevel = roomLevelBiz.getRoomLevelById(id);
        model.addAttribute("roomLevel", roomLevel);
        return "roomLevel/room_level_edit";
    }

    //做添加操作
    @RequestMapping(value = "roomLevel/doEdit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doEdit(RoomLevel roomLevel) {
        roomLevel.setUpdateDate(new Date());
        roomLevelBiz.updateRoomLevel(roomLevel);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("rel", "roomLevelBox");
        jsonObject.put("callbackType", "closeCurrent");
        jsonObject.put("refresh", true);//刷新树形菜单
        jsonObject.put("option", "roomLevel");
        jsonObject.put("treeId", "btroomLevelTree");
        jsonObject.put("url", "ajax/roomLevel/findAll");
        jsonObject.put("selectId", "roomLevel" + roomLevel.getRoomLevel().getId());

        return jsonObject.toJSONString();
    }

    public RoomLevelBiz getRoomLevelBiz() {
        return roomLevelBiz;
    }

    public void setRoomLevelBiz(RoomLevelBiz roomLevelBiz) {
        this.roomLevelBiz = roomLevelBiz;
    }
}
