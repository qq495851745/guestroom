package com.bateng.guestroom;

import com.bateng.guestroom.biz.*;
import com.bateng.guestroom.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBizTest {

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private DeclarationFormPhotoBiz declarationFormPhotoBiz;

    @Autowired
    private DeclarationFormBiz declarationFormBiz;

    @Autowired
    private RoomAndRoomLevelBiz roomAndRoomLevelBiz;

    @Autowired
    private UserLevelBiz userLevelBiz;

    @Autowired
    private RoomBiz roomBiz;

    @Autowired
    private  RepairFormBiz repairFormBiz;


    @Test
    @Transactional
    public void addUserTest(){
       declarationFormBiz.findDeclarationFormByPage(new PageVo(),new DeclarationForm());
    }

    @Test
    @Transactional
    public void getUserByIdTest(){
           char c ='a';
        System.out.println((int)c);
    }

    public UserBiz getUserBiz() {
        return userBiz;
    }

    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }

    public RoomBiz getRoomBiz() {
        return roomBiz;
    }

    public void setRoomBiz(RoomBiz roomBiz) {
        this.roomBiz = roomBiz;
    }

    public RoomAndRoomLevelBiz getRoomAndRoomLevelBiz() {
        return roomAndRoomLevelBiz;
    }

    public void setRoomAndRoomLevelBiz(RoomAndRoomLevelBiz roomAndRoomLevelBiz) {
        this.roomAndRoomLevelBiz = roomAndRoomLevelBiz;
    }

    public UserLevelBiz getUserLevelBiz() {
        return userLevelBiz;
    }

    public void setUserLevelBiz(UserLevelBiz userLevelBiz) {
        this.userLevelBiz = userLevelBiz;
    }
}
