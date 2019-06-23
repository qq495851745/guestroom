package com.bateng.guestroom.entity;

import org.dom4j.dtd.Decl;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 房间设置
 */
@Entity
@Table(name = "t_room")
public class Room {

    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "rname")
    private String name;//房号

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "room")
    //@JoinColumn(name="room_id")
    private List<RoomPhoto> roomPhotos;

    @OneToMany(cascade = CascadeType.ALL)//(cascade = CascadeType.ALL)
    @JoinColumn(name="room_id")
    private List<RoomAndRoomLevel> roomAndRoomLevels;//对应关系


    @Column(name = "description")
    private String des;//房间描述



    @Column(name = "delflag")
    private int flag=1;//删除标记

    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate=new Date();

    @Column(name="updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate=new Date();

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<DeclarationForm> declarationForms;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoomPhoto> getRoomPhotos() {
        return roomPhotos;
    }

    public void setRoomPhotos(List<RoomPhoto> roomPhotos) {
        this.roomPhotos = roomPhotos;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<RoomAndRoomLevel> getRoomAndRoomLevels() {
        return roomAndRoomLevels;
    }

    public void setRoomAndRoomLevels(List<RoomAndRoomLevel> roomAndRoomLevels) {
        this.roomAndRoomLevels = roomAndRoomLevels;
    }

    public List<DeclarationForm> getDeclarationForms() {
        return declarationForms;
    }

    public void setDeclarationForms(List<DeclarationForm> declarationForms) {
        this.declarationForms = declarationForms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
