package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer id;

    @Column(name = "username")
    private String username; //用户名

    @Column(name = "upassword")
    private String password; //密码

    @Column(name = "realname")
    private String realName;//真实姓名

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;//角色

    @ManyToOne
    @JoinColumn(name="user_level_id")
    private UserLevel userLevel;//所属用户层级

    @Column(name = "delflag")
    private int flag=1;//删除标记

    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name="updatetime")
    @Temporal(TemporalType.DATE)
    private Date updateDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
