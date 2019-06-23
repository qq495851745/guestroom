package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.RoomRepository;
import com.bateng.guestroom.entity.Room;
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







}
