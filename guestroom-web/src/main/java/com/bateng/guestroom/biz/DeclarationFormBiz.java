package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.vo.RoomOptionVo;
import org.dom4j.dtd.Decl;

import java.util.Date;
import java.util.List;

public interface DeclarationFormBiz {

    /**
     * 查看排名前十的数据
     * @param roomOptionVo
     * @return
     */
    public String findTen(RoomOptionVo roomOptionVo);

    /**
     * 生成类别的报修次数
     * @return
     */
    public PageVo findByRoomOptionCountByPage(PageVo<DeclarationForm> pageVo, RoomOptionVo roomOptionVo);


    /**
     * 根据ID，更新完成时间
     * @param declarationFormId
     * @param finishDate
     */
    public void updateFinishDate(Integer declarationFormId, Date finishDate);
    /**
     * 分页查询
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo,DeclarationForm declarationForm);

    /**
     * 根据报修次数搜索
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage4(PageVo<DeclarationForm> pageVo,DeclarationForm declarationForm);
    /**
     * 两次维修记录以上的房间
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage1(PageVo<DeclarationForm> pageVo,DeclarationForm declarationForm);

    public List<DeclarationForm> findDeclarationForms(DeclarationForm declarationForm);

    /**
     * 查询两次维修记录的房间
     * @param declarationForm
     * @return
     */
    public List<DeclarationForm> findDeclarationForms1(DeclarationForm declarationForm);

    /**
     * 统计维修记录
     * @param declarationForm
     * @return
     */
    public List<DeclarationForm> findDeclarationForms4(DeclarationForm declarationForm);
    /**
     * 添加
     * @param declarationForm
     */
    public void saveDeclarationForm(DeclarationForm declarationForm);

    /**
     * 删除
     * @param id
     */
    public void deleteById(int id);

    /**
     * 查询
     * @param id
     * @return
     */
    public DeclarationForm getDeclarationFormById(int id);

    /**
     * 修改
     * @param declarationForm
     */
    public void updateDeclarationForm(DeclarationForm declarationForm);

    /**
     * 更新状态
     */
    public void updateStatus(DeclarationForm declarationForm);

    /**
     * 查询ajax
     * @param roomOptionVo
     * @return
     */
    public String findAjaxIndex02(RoomOptionVo roomOptionVo);
}
