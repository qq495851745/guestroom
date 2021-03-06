package com.bateng.guestroom;

import com.bateng.guestroom.biz.RoomBiz;
import com.bateng.guestroom.biz.RoomOptionBiz;
import com.bateng.guestroom.entity.RoomOption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoomOptionBizTest {

    @Autowired
    private RoomOptionBiz roomOptionBiz;

    @Autowired
    private RoomBiz roomBiz;

    @Test
//    @Transactional
    public void test(){
//        System.out.println(roomBiz.findAllAjax(1));
//        roomOptionBiz.findRoomOption1Ajax();
        roomOptionBiz.updatePinyin();
    }



    public RoomOptionBiz getRoomOptionBiz() {
        return roomOptionBiz;
    }

    public void setRoomOptionBiz(RoomOptionBiz roomOptionBiz) {
        this.roomOptionBiz = roomOptionBiz;
    }
}
