package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.vo.RoomOptionVo;

import java.util.List;

public interface DeclarationFormRepository {

    /**
     * 分页显示根据报修类别，显示报修次数。
     * @param declarationForm
     * @return
     */
    public PageVo findByRoomOptionCountByPage(PageVo<DeclarationForm> pageVo, RoomOptionVo roomOptionVo);

    /**
     * 做分页
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm);

    /**
     * 根据报修次数搜索
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage4(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm);

    /**
     * 分页查询两次维修记录的房间
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage1(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm);

    public List<DeclarationForm> findDeclarationForms(DeclarationForm declarationForm);

    /**
     * 查询所有两次维修记录的房间
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

}
