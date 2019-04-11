package com.bateng.guestroom;

import com.bateng.guestroom.biz.RoleBiz;
import com.bateng.guestroom.entity.Menu;
import com.bateng.guestroom.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleBizTest {
    @Autowired
    private RoleBiz roleBiz;

    @Test
    public void test() {
        List<Role> roles=roleBiz.findRole();
        System.out.println(roles.size());
    }
}
