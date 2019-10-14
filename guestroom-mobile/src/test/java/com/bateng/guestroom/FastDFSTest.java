package com.bateng.guestroom;

import com.bateng.guestroom.config.util.FastDFSClient;
import org.junit.Test;

import java.io.File;

public class FastDFSTest {

    @Test
      public void upload(){
        System.out.println("上次");

        String id=FastDFSClient.uploadFile(new File("C:\\Users\\fjl01\\Desktop\\2012031220134655.jpg"),"2012031220134655.jpg");

        System.out.println(id);
      }
}
