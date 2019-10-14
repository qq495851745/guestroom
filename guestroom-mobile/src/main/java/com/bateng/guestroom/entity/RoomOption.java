package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 房间部位
 */
@Entity
@Table(name = "t_room_option")
public class RoomOption {
    @Id
    @Column(name = "roid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "roname")
    private String name; // 部位名称

    @Column(name = "delflag")
    private int flag;//删除标记

    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name="updatetime")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private RoomOption roomOption;

    @Column(name = "rodes")
    private String desprition;//客房描述信息

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

    public RoomOption getRoomOption() {
        return roomOption;
    }

    public void setRoomOption(RoomOption roomOption) {
        this.roomOption = roomOption;
    }

    public String getDesprition() {
        return desprition;
    }

    public void setDesprition(String desprition) {
        this.desprition = desprition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomOption that = (RoomOption) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
