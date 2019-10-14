package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.UserLevel;

import java.util.List;

public interface UserLevelBiz {

    /**
     * 返回所有数据
     * @return
     */
    public String findAllUserLevelAjax();

    /**
     * 查找指定层级下的所有UserLevel
     * @param pid
     * @return
     */
    public String findAllUserLevelAjaxByPid(int pid);


    /**
     * 分页查询
     * @param pageVo
     * @param userLevel
     * @return
     */
    public PageVo<UserLevel> findUserLevelByPage(PageVo<UserLevel> pageVo,UserLevel userLevel);


    public void saveUserLevel(UserLevel userLevel);


    public List<UserLevel> findSubUserLevelByid(int id);

    /**
     * 根据id删除
     * @param id
     */
    public void deleteUserLevelById(int id);

    /**
     * 获取对象
     * @param id
     * @return
     */
    public UserLevel getUserLevelById(int id);

    /**
     * 更新
     * 除了创建时间
     * @param userLevel
     */
    public void updateUserLevel(UserLevel userLevel);


}
