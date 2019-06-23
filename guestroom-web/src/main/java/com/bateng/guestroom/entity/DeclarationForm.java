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
    private String formName;//名称   报修工程内容

    @Column(name="ddescription")
    private String description;//报修单说明

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;//关联到某个房间

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;//报修人

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;//审核人


    @ManyToOne
    @JoinColumn(name = "roomoption_id")
    private RoomOption roomOption;//关联到房间某个地方

    @ManyToOne
    @JoinColumn(name = "fornameoption_id")
    private RoomOption forNameOption;//工程报修位置


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
    private DeclarationFormStatus declarationFormStatus;//报修状态

    @Transient
    private List<Integer> declarationFormStatusList;//搜索条件 状态


    @ManyToOne
    @JoinColumn(name = "appoint_form_id")
    private AppointForm appointForm;//最近委派单

    @ManyToOne
    @JoinColumn(name = "repair_form_id")
    private RepairForm repairForm;//最近维修单

    @OneToMany
    @JoinColumn(name="declaration_form_id")
    private List<RepairForm> repairForms;

    @Column(name = "finish_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;//要求完成时间

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;//实际最后完成并审核通过时间



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

    public AppointForm getAppointForm() {
        return appointForm;
    }

    public void setAppointForm(AppointForm appointForm) {
        this.appointForm = appointForm;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public RepairForm getRepairForm() {
        return repairForm;
    }

    public void setRepairForm(RepairForm repairForm) {
        this.repairForm = repairForm;
    }

    public List<RepairForm> getRepairForms() {
        return repairForms;
    }

    public void setRepairForms(List<RepairForm> repairForms) {
        this.repairForms = repairForms;
    }

    public List<Integer> getDeclarationFormStatusList() {
        return declarationFormStatusList;
    }

    public void setDeclarationFormStatusList(List<Integer> declarationFormStatusList) {
        this.declarationFormStatusList = declarationFormStatusList;
    }

    public RoomOption getForNameOption() {
        return forNameOption;
    }

    public void setForNameOption(RoomOption forNameOption) {
        this.forNameOption = forNameOption;
    }
}
