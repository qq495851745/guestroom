package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * UserLevel 用户层级设置
 */
@Table(name="t_user_level")
@Entity
public class UserLevel {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uname")
    private String name;//层级名

    @Column(name = "ucreatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate=new Date();

    @Column(name="updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate=new Date();

    @Column(name="des")
    private String des;

    @ManyToOne
    @JoinColumn(name = "pid")
    private UserLevel userLevel;

    @OneToMany
    @JoinColumn(name="pid")
    private List<UserLevel> userLevels;

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

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<UserLevel> getUserLevels() {
        return userLevels;
    }

    public void setUserLevels(List<UserLevel> userLevels) {
        this.userLevels = userLevels;
    }
}
