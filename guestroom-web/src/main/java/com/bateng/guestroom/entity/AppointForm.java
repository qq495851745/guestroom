package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 委派单
 * 报修单委派给谁负责处理
 */
@Entity
@Table(name = "t_appoint_form")
public class AppointForm {
    @Id
    @Column(name = "aid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "des")
    private String description;//委派说明

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;//委派给谁

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;//委派人

    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;//委派时间


    @ManyToOne
    @JoinColumn(name = "declaration_form_id")
    private DeclarationForm declarationForm;//关联报修单


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
