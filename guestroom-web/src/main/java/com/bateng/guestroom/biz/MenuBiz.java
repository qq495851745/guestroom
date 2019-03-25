package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.Menu;

import java.util.List;

public interface MenuBiz {

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    public Menu getMenuById(int id);


    public void updateMenu(Menu menu);

    /**
     * 查询所有菜单
     * @return
     */
    public List<Menu> findMenus();


    /**
     * 获取json
     *
     */

     public String findMenusAjax();

    /**
     * 根据用户ID获取菜单
     * @param id
     * @return
     */
     public String findMenusByUserIdAjax(int id);






}
