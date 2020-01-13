package com.bateng.guestroom.controller.view;

import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.biz.RoomBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.interceptor.DatePropertyEditor;
import com.bateng.guestroom.config.interceptor.DatePropertyEditor2;
import com.bateng.guestroom.entity.vo.RoomOptionVo;
import com.bateng.guestroom.entity.vo.RoomVo;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/guestroom")
public class RoomViewController extends BaseController {

    @Autowired
    public RoomBiz roomBiz;
    @Autowired
    public DeclarationFormBiz declarationFormBiz;


    @RequestMapping(value = "/view/room/count/ten",method ={RequestMethod.GET,RequestMethod.POST})
    public String index03(RoomOptionVo roomOptionVo,Model model){
        model.addAttribute("roomOptionVo",roomOptionVo);
        return "view/room/ten";
    }

    @RequestMapping(value = "ajax/view/room/count/ten",method ={RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    @ResponseBody
    public String index04(RoomOptionVo roomOptionVo){

        return  declarationFormBiz.findTen(roomOptionVo);
    }

    @RequestMapping(value = "/view/repair",method = {RequestMethod.GET,RequestMethod.POST})
    public String index02(RoomOptionVo roomOptionVo,Model model){
        model.addAttribute("roomOptionVo",roomOptionVo);
        return  "view/room/repair";
    }


    @RequestMapping(value = "/ajax/view/repair",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String ajaaIndex02(RoomOptionVo roomOptionVo, Model model){

        return declarationFormBiz.findAjaxIndex02(roomOptionVo);
    }
    /**
     * 横轴房间，纵轴次数
     *
     * @return
     */
    @RequestMapping(value = "/view/room/count", method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json;charset=utf-8")
    public String index01(RoomVo roomVo, Model model) {
        model.addAttribute("roomVo",roomVo);
        return "view/room/count";
    }

    /**
     * ajax返回数据
     *
     * @return
     */
    @RequestMapping(value = "/ajax/view/room/count", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ajaxIndex01(RoomVo roomVo) {
        return roomBiz.findAjaxIndex01(roomVo);
    }

    public RoomBiz getRoomBiz() {
        return roomBiz;
    }

    public void setRoomBiz(RoomBiz roomBiz) {
        this.roomBiz = roomBiz;
    }

    public DeclarationFormBiz getDeclarationFormBiz() {
        return declarationFormBiz;
    }

    public void setDeclarationFormBiz(DeclarationFormBiz declarationFormBiz) {
        this.declarationFormBiz = declarationFormBiz;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,"time01",new DatePropertyEditor2());
        binder.registerCustomEditor(Date.class,"time02",new DatePropertyEditor2());
    }
}
