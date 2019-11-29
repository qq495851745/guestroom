package com.bateng.guestroom.entity.vo;

import java.util.Date;

public class RoomVo {
    private String name;

    private Date time01;
    private Date time02;

    public Date getTime01() {
        return time01;
    }

    public void setTime01(Date time01) {
        this.time01 = time01;
    }

    public Date getTime02() {
        return time02;
    }

    public void setTime02(Date time02) {
        this.time02 = time02;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
