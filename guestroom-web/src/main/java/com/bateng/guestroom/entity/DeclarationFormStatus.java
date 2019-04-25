package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 表示当前报修单状态
 */
@Entity
@Table(name="t_declaration_form_status")
public class DeclarationFormStatus {
    @Id
    @Column(name = "sid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dstatus")
    private String status;

    @Column(name = "dcreatedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public DeclarationFormStatus(String status) {
        this.status = status;
    }

    public DeclarationFormStatus(int id){
        this.id=id;
    }
    public DeclarationFormStatus() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
