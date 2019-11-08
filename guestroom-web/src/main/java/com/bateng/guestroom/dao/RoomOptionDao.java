package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.RoomOptionRepository;
import com.bateng.guestroom.entity.RoomOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomOptionDao extends JpaRepository<RoomOption,Integer>, JpaSpecificationExecutor<RoomOption> , RoomOptionRepository {
    /**
     * 根据删除标记查询所有客房选项
     * @param flag
     * @return
     */
    public List<RoomOption> findAllByFlag(int flag);

    @Query("from RoomOption ro where  ro.roomOption.id=:pId and ro.flag=1")
    public Page<RoomOption> findPageByPid(@Param("pId") int pId, Pageable pageable);

    /**
     * 查看pid下是否有子元素
     * @param pid
     * @return
     */
    @Query("from RoomOption  ro where ro.roomOption.id=?1 and ro.flag=1")
    public List<RoomOption> findAllByPid(int pid);


    /**
     * 将删除标记设为0
     */
    @Modifying
    @Query("update RoomOption  ro set ro.flag=0 where ro.id=?1")
    public void deleteFlagById(int id);

    /**
     * 根据名字查找RoomOption
     * @param name
     * @return
     */
    public List<RoomOption> findByName(String name);


}
