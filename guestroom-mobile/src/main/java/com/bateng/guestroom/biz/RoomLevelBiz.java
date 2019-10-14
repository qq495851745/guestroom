package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.RoomLevel;

import java.util.List;
import java.util.Map;

public interface RoomLevelBiz {
    /**
     * 查询有效的层级
     * @param flag
     * @return
     */
    public List<RoomLevel> findAllByFlag(int flag);


    /**
     * 返回json结构
     * @param flag
     * @return
     */
    public String findAllByFlagAjax(int flag);

    /**
     * 分页
     * 带条件查询
     */

    public PageVo<RoomLevel> findALLByPageAndCon(PageVo<RoomLevel> pageVo,RoomLevel roomLevel);


    /**
     * 添加RoomLevel
     */

    public  void saveRoomLevel(RoomLevel roomLevel);

    /**
     * 是否发现有子元素  根据id
     * @return
     */
    public boolean isFindChild(int id);

    /**
     * 根据id删除RoomLevel
     * @param id
     */
    public void deleteRoomLevelById(int id);

    /**
     * 根据id找RoomLevel
     * @param id
     * @return
     */
    public RoomLevel getRoomLevelById(int id);

    /**
     * 更新操作
     * @param roomLevel
     */
    public void updateRoomLevel(RoomLevel roomLevel);

    /**
     * 根据删除标记和rel获取所有层级
     * @param flag
     * @param rel
     * @return
     */
    public String findAllRoomLevelAjax(int flag, Map<String,String> rel);

}
