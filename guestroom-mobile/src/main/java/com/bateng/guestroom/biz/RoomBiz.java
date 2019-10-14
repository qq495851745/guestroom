package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.RoomAndRoomLevel;

import java.util.List;

public interface RoomBiz {

    /**
     * 根据RoomLevelid找到对应的房间
     * @param id
     * @return
     */
    public List<Room> findRoomByRoomLevel(int id);


    /**
     * 分页查询，根据RoomLevel
     * @param pageVo
     * @param roomAndRoomLevel
     * @return
     */
    public PageVo<Room> findRoomByPage(PageVo<Room> pageVo, RoomAndRoomLevel roomAndRoomLevel);

    /**
     * 分页查询，根据Room
     * @param pageVo
     * @param room
     * @return
     */
    public PageVo<Room> findRoomByPage(PageVo<Room> pageVo,Room room);

    /**
     * 添加房间，成功返回true
     * @param room
     * @return
     */
    public Room addRoom(Room room);

    /**
     * 根据id获取room
     * @param id
     * @return
     */
    public Room getRoomById(int id);

    /**
     * 删除Room
     * @param id room的id
     * @return
     */
    public void deleteRoomById(int id);

    /**
     * 分步更新room
     * @param room
     */
    public void updateRoom(Room room);

    /**
     * 根据房号查找房间是否存在
     * @param name
     * @return
     */
    public Room getRoomByName(String name);

    /**
     * 报表查询房间
     * @param name
     * @return
     */
    public Room findRoomForReoprtByName(String name);

}
