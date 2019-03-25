package com.bateng.guestroom.controller;

import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.RoomLevel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        /*try {
            String client=FastDFSClient.uploadFile(file.getInputStream(),file.getOriginalFilename());
            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return  "";
    }



}
