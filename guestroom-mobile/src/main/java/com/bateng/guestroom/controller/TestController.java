package com.bateng.guestroom.controller;

import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.RoomLevel;
import org.springframework.data.repository.query.Param;
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

    @RequestMapping("/upload")
public String test(@RequestParam("fm") MultipartFile file ){
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        try {
         String path=   FastDFSClient.uploadFile(file.getInputStream(),file.getOriginalFilename());
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "";
}
}
