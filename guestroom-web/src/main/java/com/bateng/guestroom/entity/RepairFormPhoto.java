package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_repair_form_photo")
public class RepairFormPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    private int id;

    @Column(name="dfppath")
    private String path;

    @Column(name="dfpext")
    private String ext;//扩展名

    @Column(name = "dfporigname")
    private String origName;//原来的文件名


    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate=new Date();

    @ManyToOne
    @JoinColumn(name = "repair_form_id")
    private  RepairForm repairForm;//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public RepairForm getRepairForm() {
        return repairForm;
    }

    public void setRepairForm(RepairForm repairForm) {
        this.repairForm = repairForm;
    }
}
