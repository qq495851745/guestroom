package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 维修单
 */
@Entity
@Table(name = "t_repair_form")
public class RepairForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    private  int id;

    @Column(name = "description")
    private String description;//维修描述

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;//维修人

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;//审核人

    @ManyToOne
    @JoinColumn(name = "declaration_form_id")
    private DeclarationForm declarationForm;

    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @JoinColumn(name = "appoint_form_id")
    @ManyToOne
    private AppointForm appointForm;//关联委派单

    @ManyToOne
    @JoinColumn(name = "pid")
    private RepairForm repairForm;

    @ManyToOne
    @JoinColumn(name = "declaration_form_status_id")
    private DeclarationFormStatus declarationFormStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "repair_form_id" )
    private List<RepairFormPhoto> repairFormPhotos;

    @Column(name = "s_type")
    private int type=1;//1 维修单 2 审核单

    public RepairForm(){}
    public RepairForm(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public DeclarationForm getDeclarationForm() {
        return declarationForm;
    }

    public void setDeclarationForm(DeclarationForm declarationForm) {
        this.declarationForm = declarationForm;
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

    public DeclarationFormStatus getDeclarationFormStatus() {
        return declarationFormStatus;
    }

    public void setDeclarationFormStatus(DeclarationFormStatus declarationFormStatus) {
        this.declarationFormStatus = declarationFormStatus;
    }

    public List<RepairFormPhoto> getRepairFormPhotos() {
        return repairFormPhotos;
    }

    public void setRepairFormPhotos(List<RepairFormPhoto> repairFormPhotos) {
        this.repairFormPhotos = repairFormPhotos;
    }

    public AppointForm getAppointForm() {
        return appointForm;
    }

    public void setAppointForm(AppointForm appointForm) {
        this.appointForm = appointForm;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
