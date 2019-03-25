package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 基础数据设置
 */
@Entity
@Table(name = "t_data_setting")
public class DataSetting {

    @Id
    @Column(name = "sid")
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int id;

    @Column(name = "skey")
    private String key;

    @Column(name="svalue")
    private String value;

    @Column(name = "sdes")
    private String des;//说明



    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name="updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
}
