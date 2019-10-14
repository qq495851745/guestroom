package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.UserLevelRepository;
import com.bateng.guestroom.entity.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLevelDao  extends JpaRepository<UserLevel,Integer> , UserLevelRepository {

    /**
     * 查找是否存在子项目
     * @param userLevelId
     * @return
     */
    public List<UserLevel> findAllByUserLevelId(int userLevelId);



}
