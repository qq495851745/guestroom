package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import  java.util.List;

@Entity
@Table(name="t_role")
public class Role {
    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rname")
    private String name;

    @Column(name = "delflag")
    private int flag;//删除标记

    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name="updatetime")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @ManyToMany
    @JoinTable(name = "t_role_menu",joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "rid"),
    inverseJoinColumns = @JoinColumn(name = "menu_id",referencedColumnName = "mid"))
    private List<Menu> menus;

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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
