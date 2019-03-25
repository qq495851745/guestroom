package com.bateng.guestroom.entity;

import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.ValueFilter;

import javax.persistence.*;
import java.util.Date;

/**
 * 房间层级菜单
 */
@Entity
@Table(name = "t_room_level")
public class RoomLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rlid")
    private Integer id;
    @Column(name = "rlname")
    private String name;//名称
    @Column(name = "rldes")
    private String des;//描述

    @Column(name = "delflag")
    private int flag=1;//删除标记

    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name="updatetime")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "pId")
    private  RoomLevel roomLevel;


    public RoomLevel getRoomLevel() {
        return roomLevel;
    }

    public void setRoomLevel(RoomLevel roomLevel) {
        this.roomLevel = roomLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public RoomLevel(){}

    public  RoomLevel(int id){
        this.id=id;
    }


    //fastjson过滤器
    public static class PropertyFilter implements com.alibaba.fastjson.serializer.PropertyFilter{
        @Override
        public boolean apply(Object o, String s, Object o1) {
            if (s.equals("createDate"))
                return false;
            else if (s.equals("updateDate"))
                return false;
            else if (s.equals("flag"))
                return false;
            else if (s.equals("des"))
                return false;
            else
                return true;
        }
    }

    public static class NameFilter implements com.alibaba.fastjson.serializer.NameFilter{
        @Override
        public String process(Object o, String s, Object o1) {
            if (s.equals("roomLevel"))
                return "pid";
            else
                return s;
        }
    }

    public static class ValueFilter implements com.alibaba.fastjson.serializer.ValueFilter{
        @Override
        public Object process(Object o, String s, Object o1) {
            if (s.equals("pid"))
                if (o1 != null)
                    return ((RoomLevel) o1).getId();
                else
                    return 0;
            else
                return o1;
        }
    }
}
