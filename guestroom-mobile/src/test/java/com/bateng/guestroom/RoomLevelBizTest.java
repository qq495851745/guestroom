package com.bateng.guestroom;

import com.bateng.guestroom.biz.RoomLevelBiz;
import com.bateng.guestroom.biz.RoomOptionBiz;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.RoomLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoomLevelBizTest {

    @Autowired
    private RoomLevelBiz roomLevelBiz;
    @Test
    @Transactional
    public void test(){

    }

}
