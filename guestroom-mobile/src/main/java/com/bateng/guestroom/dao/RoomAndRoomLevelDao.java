package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.RoomAndRoomLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomAndRoomLevelDao extends JpaRepository<RoomAndRoomLevel,Integer> {


    public void deleteByRoomId(int roomId);


}
