package com.bateng.guestroom.entity;

import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;//关联到某个房间

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private  User user;//报修人

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id")
    private User user2;//审核人


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomoption_id")
    private RoomOption roomOption;//关联到房间某个地方   报修类别

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornameoption_id")
    private RoomOption forNameOption;//工程报修位置


    @Column(name = "delflag")
    private Integer flag=1;//删除标记


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "declarationform_id")
    private List<DeclarationFormPhoto> declarationFormPhotos;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "declaration_form_id")
    private List<AppointForm> appointForms;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dcreatedate")
    private Date createDate=new Date();//创建时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dupdatedate")
    private Date updateDate = new Date();//更新时间

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "actualdate")
    private Date actualDate;//实际发生时间

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dfstatus")
    private DeclarationFormStatus declarationFormStatus;//报修状态

    @Transient
    private List<Integer> declarationFormStatusList;//搜索条件 状态


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appoint_form_id")
    private AppointForm appointForm;//最近委派单

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repair_form_id")
    private RepairForm repairForm;//最近维修单

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="declaration_form_id")
    private List<RepairForm> repairForms;

    @Column(name = "finish_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;//要求完成时间

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;//实际最后完成并审核通过时间


    @Transient
    private Date time01;//用于搜索条件

    @Transient
    private Date time02;//用于搜索条件

    @Transient
    private int count;//统计总数

    @Transient
    private Long count01;//起始次数
    @Transient
    private Long count02;//最后次数

    /*@ManyToOne
    @JoinColumn(name = "project_user_id")
    private User projectUser;//维修人*/

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

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

    public Date getTime01() {
        return time01;
    }

    public void setTime01(Date time01) {
        this.time01 = time01;
    }

    public Date getTime02() {
        return time02;
    }

    public void setTime02(Date time02) {
        this.time02 = time02;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getCount01() {
        return count01;
    }

    public void setCount01(Long count01) {
        this.count01 = count01;
    }

    public Long getCount02() {
        return count02;
    }

    public void setCount02(Long count02) {
        this.count02 = count02;
    }
}
