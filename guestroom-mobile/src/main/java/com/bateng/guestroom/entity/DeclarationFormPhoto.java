package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "t_declarationform_photo")
public class DeclarationFormPhoto {

    @Id
    @Column(name="dfpid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name="dfppath")
    private String path;

    @Column(name="dfpext")
    private String ext;//扩展名

    @Column(name = "dfporigname")
    private String origName;//原来的文件名


    @Column(name = "createdate")
    @Temporal(TemporalType.DATE)
    private Date createDate=new Date();

    @ManyToOne
    @JoinColumn(name = "declarationform_id")
    private DeclarationForm declarationForm;

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

    public DeclarationForm getDeclarationForm() {
        return declarationForm;
    }

    public void setDeclarationForm(DeclarationForm declarationForm) {
        this.declarationForm = declarationForm;
    }
}
