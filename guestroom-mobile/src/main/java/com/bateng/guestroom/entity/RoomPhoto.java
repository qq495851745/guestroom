package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 *房间相片
 */
@Entity
@Table(name = "t_room_photo")
public class RoomPhoto {

    @Id
    @Column(name="rpid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name="rppath")
    private String path;

    @Column(name="rpext")
    private String ext;//扩展名

    @Column(name = "origname")
    private String origName;//原来的文件名
    @ManyToOne
    @JoinColumn(name = "room_id")
    private  Room room;

    @Column(name = "delflag")
    private int flag=1;//删除标记

    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createDate=new Date();

    @Column(name="updatetime")
    @Temporal(TemporalType.DATE)
    private Date updateDate=new Date();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getOrigName() {
        return origName;
    }

    public void setOrigName(String origName) {
        this.origName = origName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomPhoto roomPhoto = (RoomPhoto) o;
        return Objects.equals(id, roomPhoto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
