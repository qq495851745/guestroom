package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;
import org.dom4j.dtd.Decl;

public interface DeclarationFormBiz {
    /**
     * 分页查询
     * @param pageVo
     * @param declarationForm
     * @return
     */
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo,DeclarationForm declarationForm);

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
}
