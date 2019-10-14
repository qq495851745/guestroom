package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.RoomLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomLevelDao extends JpaRepository<RoomLevel,Integer>, JpaSpecificationExecutor<RoomLevel> {

    public List<RoomLevel> findAllByFlag(int flag);

    /**
     * 查找pid下有无子元素
     * @param pid
     * @return
     */
    @Query("from RoomLevel  rl where rl.roomLevel.id=?1 and rl.flag=1")
    public  List<RoomLevel> findRoomLevelBypId(int pid);

    /**
     * 通过删除标记删除
     * @param id
     */
    @Modifying
    @Query("update  RoomLevel  rl set rl.flag=0 where rl.id=?1")
    public void deleteRoomLevelById(int id);

    /**
     * 部分数据更新
     * @param
     */
    @Modifying
    @Query("update  RoomLevel  rl  set rl.name=?1,rl.des=?2,rl.updateDate=?3 where rl.id=?4")
    public void updateRoomLevel(String name, String des, Date update,int id);
}
