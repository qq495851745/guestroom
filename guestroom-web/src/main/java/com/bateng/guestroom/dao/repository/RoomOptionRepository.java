package com.bateng.guestroom.dao.repository;

import com.bateng.guestroom.entity.vo.RoomOptionVo;

import java.util.List;

public interface RoomOptionRepository {
    /**
     * 查询所有房间属性
     * @return
     */
    public List findRoomOptionVo(RoomOptionVo roomOptionVo);
}
