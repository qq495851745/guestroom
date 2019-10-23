package com.bateng.guestroom.entity;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Column(name = "pinyin")
    private String pinyin;//拼音字母列表

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private List<RoomOption> roomOptions;

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

    public List<RoomOption> getRoomOptions() {
        return roomOptions;
    }

    public void setRoomOptions(List<RoomOption> roomOptions) {
        this.roomOptions = roomOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomOption that = (RoomOption) o;
        return Objects.equals(id, that.id);
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
