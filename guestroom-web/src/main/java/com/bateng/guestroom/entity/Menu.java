package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_menu")
public class Menu {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "mid")
     private  Integer id;

     @Column(name = "mname")
     private String name;

     @Column(name = "murl")
     private String url;

    @Column(name = "delflag")
    private int flag;//删除标记

    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name="updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name="mtarget")
    private String target;//dwz属性

    @Column(name = "mrel")
    private String rel;//dwz属性

    @Column(name = "orderby1")
    private int orderby1;//排序属性

    @ManyToOne
    @JoinColumn(name = "pid")
    private Menu menu;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private List<Menu> menus;

    @ManyToMany(mappedBy = "menus")
    private List<Role> roles;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getOrderby1() {
        return orderby1;
    }

    public void setOrderby1(int orderby1) {
        this.orderby1 = orderby1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
