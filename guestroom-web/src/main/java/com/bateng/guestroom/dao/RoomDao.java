package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.RoomRepository;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.vo.RoomVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomDao extends RoomRepository, JpaRepository<Room,Integer>, JpaSpecificationExecutor<Room> {
    /**
     * 根据roomLevel的id找对应的Room
     * @param id
     * @return
     */
    @Query("select  r.room  from RoomAndRoomLevel r where r.roomLevel.id=?1")
    public List<Room> findRoomByRoomLevel(int id);

    public Room findRoomByName(String name);

//    @Query(value = "select t1.rid ,t1.rname, count(*) as c from t_room t1 left outer join t_declaration_form t2 on (t1.rid = t2.room_id and t1.delflag=1 and t2.delflag=1) GROUP BY t1.rid ,t1.rname\n",nativeQuery = true)








}
