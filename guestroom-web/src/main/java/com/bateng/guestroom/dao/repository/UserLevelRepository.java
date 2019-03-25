package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.UserLevel;

import java.util.List;

public interface UserLevelRepository {

    /**
     * 分页查询
     * @param pageVo
     * @param userLevel
     * @return
     */
    public PageVo<UserLevel> findUserLevelByPage(PageVo<UserLevel> pageVo, UserLevel userLevel);
}
