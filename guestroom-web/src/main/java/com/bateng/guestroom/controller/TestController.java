package com.bateng.guestroom.controller;

import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.RoomLevel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping(value = {"/abc","/efg"})
public String test(HttpServletRequest request){
        System.out.println(request.getPathInfo() +"             sfwe");
        System.out.println(request.getContextPath()+"   abc");
        System.out.println(request.getRequestURL());
        System.out.println(request.getServletPath());

    return "";
}
}
