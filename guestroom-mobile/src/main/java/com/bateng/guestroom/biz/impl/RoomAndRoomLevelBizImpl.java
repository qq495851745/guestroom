package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.RoomAndRoomLevelBiz;
import com.bateng.guestroom.dao.RoomAndRoomLevelDao;
import com.bateng.guestroom.entity.RoomAndRoomLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomAndRoomLevelBiz")
public class RoomAndRoomLevelBizImpl implements RoomAndRoomLevelBiz {

    @Autowired
    private RoomAndRoomLevelDao roomAndRoomLevelDao;


    public RoomAndRoomLevelDao getRoomAndRoomLevelDao() {
        return roomAndRoomLevelDao;
    }

    public void setRoomAndRoomLevelDao(RoomAndRoomLevelDao roomAndRoomLevelDao) {
        this.roomAndRoomLevelDao = roomAndRoomLevelDao;
    }
}
