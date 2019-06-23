package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.RoomAndRoomLevel;

import java.util.List;

public interface RoomRepository {
    /**
     * 根据roomLevel列出 room并且分页
     * @param pageVo
     * @param roomAndRoomLevel
     * @return
     */
    public PageVo<Room> findRoomForRoomLevelByPage(PageVo<Room> pageVo, RoomAndRoomLevel roomAndRoomLevel);

    public PageVo<Room> findRoomForRoomByPage(PageVo<Room> pageVo,Room room);
}
