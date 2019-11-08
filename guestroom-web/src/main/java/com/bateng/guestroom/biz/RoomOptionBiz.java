package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.RoomOption;
import com.bateng.guestroom.entity.vo.RoomOptionVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomOptionBiz {

    /**
     * 用于生成报表，查询所有数据
     * @return
     */
    public List<RoomOptionVo> findAllRoomOptionVo(RoomOptionVo roomOptionVo);

    /**
     * 查询所有有效的客房属性
     * @return  json格式
     */
    public String findRoomOptionAjax();

    /**
     * 分页查找父亲下的所有儿子
     * @return
     */
    public PageVo<RoomOption> findByPageAndPid(int pageNUm, int pageSize, int pId);
    /**
     * 分页查找父亲下的所有儿子
     * @return
     */
    public PageVo<RoomOption> findByPageAndPid(PageVo<RoomOption> pageVo,RoomOption roomOption);

    /**
     * 添加
     * @param roomOption
     * @return
     */
    public void addRoomOption(RoomOption roomOption);




    /**
     * 根据Id查询出对象
     * @param id
     * @return
     */
    public RoomOption getRoomOptionById(int id);


    /**
     * 查找是否有子项目
     * 有子项返回true ,无子项返回false
     */

    public boolean findRoomOptionBypId(int pId);

    /**
     * 根据id删除
     * @param id
     */
    public void deleteRoomOptionById(int id);

    /**
     * 根据名字，查找RoomOption
     * @param name
     * @return
     */
    public List<RoomOption> findRoomOptionByName(String name);







}
