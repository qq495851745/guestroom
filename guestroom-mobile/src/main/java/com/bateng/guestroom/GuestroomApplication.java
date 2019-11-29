package com.bateng.guestroom;


import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GuestroomApplication {

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {

        SpringApplication.run(GuestroomApplication.class, args);


    }

}

