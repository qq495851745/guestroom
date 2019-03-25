package com.bateng.guestroom;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.config.util.FastDFSClient;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GuestroomApplication {

    public static void main(String[] args) {

        SpringApplication.run(GuestroomApplication.class, args);

    }

}

