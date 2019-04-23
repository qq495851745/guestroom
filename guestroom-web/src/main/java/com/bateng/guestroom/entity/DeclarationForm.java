package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 报修单实体
 */
@Entity
@Table(name = "t_declaration_form")
public class DeclarationForm {

    @Id
    @Column(name="did")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dname")
    private String formName;//名称

    @Column(name="ddescription")
    private String description;//报修单说明

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;//关联到某个房间

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;//报修人


    @ManyToOne
    @JoinColumn(name = "roomoption_id")
    private RoomOption roomOption;//关联到房间某个地方


    @Column(name = "delflag")
    private Integer flag=1;//删除标记


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "declarationform_id")
    private List<DeclarationFormPhoto> declarationFormPhotos;


    @OneToMany
    @JoinColumn(name = "declaration_form_id")
    private List<AppointForm> appointForms;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dcreatedate")
    private Date createDate=new Date();//创建时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dupdatedate")
    private Date updateDate = new Date();//更新时间

    @ManyToOne
    @JoinColumn(name = "dfstatus")
    private DeclarationFormStatus declarationFormStatus;

    /*@ManyToOne
    @JoinColumn(name = "project_user_id")
    private User projectUser;//维修人*/

    /**
     * 状态
     * 0,表示刚新建，工程未读
     * @return
     */


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomOption getRoomOption() {
        return roomOption;
    }

    public void setRoomOption(RoomOption roomOption) {
        this.roomOption = roomOption;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DeclarationFormPhoto> getDeclarationFormPhotos() {
        return declarationFormPhotos;
    }

    public void setDeclarationFormPhotos(List<DeclarationFormPhoto> declarationFormPhotos) {
        this.declarationFormPhotos = declarationFormPhotos;
    }

    public DeclarationFormStatus getDeclarationFormStatus() {
        return declarationFormStatus;
    }

    public void setDeclarationFormStatus(DeclarationFormStatus declarationFormStatus) {
        this.declarationFormStatus = declarationFormStatus;
    }

    public List<AppointForm> getAppointForms() {
        return appointForms;
    }

    public void setAppointForms(List<AppointForm> appointForms) {
        this.appointForms = appointForms;
    }
}
