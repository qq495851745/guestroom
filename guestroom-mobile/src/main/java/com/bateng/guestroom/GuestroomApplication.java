package com.bateng.guestroom;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.config.util.FastDFSClient;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GuestroomApplication {

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {

        SpringApplication.run(GuestroomApplication.class, args);


    }

}

