package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.RoomBiz;
import com.bateng.guestroom.dao.RoomAndRoomLevelDao;
import com.bateng.guestroom.dao.RoomDao;
import com.bateng.guestroom.dao.RoomPhotoDao;
import com.bateng.guestroom.dao.repository.RoomRepository;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.RoomAndRoomLevel;
import com.bateng.guestroom.entity.RoomPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service("roomBiz")
public class RoomBizImpl implements RoomBiz {

    @Autowired
    private RoomDao roomDao;
    @Autowired
    private RoomPhotoDao roomPhotoDao;

    @Autowired
    private RoomAndRoomLevelDao roomAndRoomLevelDao;

    @Autowired
    private RoomRepository roomRepository;


    @Override
    public List<Room> findRoomByRoomLevel(int id) {
        return roomDao.findRoomByRoomLevel(id);
    }

    @Override
    public PageVo<Room> findRoomByPage(PageVo<Room> pageVo, RoomAndRoomLevel roomAndRoomLevel) {
        return roomRepository.findRoomForRoomLevelByPage(pageVo,roomAndRoomLevel);
    }

    @Override
    @Transactional
    public Room addRoom(Room room) {
          return  roomDao.save(room);
    }

    @Override
    public Room getRoomById(int id) {
        return roomDao.getOne(id);
    }

    @Override
    @Transactional
    public void deleteRoomById(int id) {
        //漏写fastdfs图片删除
         roomDao.deleteById(id);
    }


    @Override
    @Transactional
    public void updateRoom(Room room) {
         //更新图片 插入图片
        for(RoomPhoto roomPhoto:room.getRoomPhotos()){
            roomPhotoDao.save(roomPhoto);
        }
        //删除旧层级
        roomAndRoomLevelDao.deleteByRoomId(room.getId());
      roomDao.save(room);


    }

    @Override
    public Room findRoomForReoprtByName(String name) {
        Room room = roomDao.findRoomByName(name);
        return null;
    }

    @Override
    public PageVo<Room> findRoomByPage(PageVo<Room> pageVo, Room room) {
        return roomDao.findRoomForRoomByPage(pageVo,room);
    }

    @Override
    public Room getRoomByName(String name) {

        return roomDao.findRoomByName(name);
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomPhotoDao getRoomPhotoDao() {
        return roomPhotoDao;
    }

    public void setRoomPhotoDao(RoomPhotoDao roomPhotoDao) {
        this.roomPhotoDao = roomPhotoDao;
    }

    public RoomAndRoomLevelDao getRoomAndRoomLevelDao() {
        return roomAndRoomLevelDao;
    }

    public void setRoomAndRoomLevelDao(RoomAndRoomLevelDao roomAndRoomLevelDao) {
        this.roomAndRoomLevelDao = roomAndRoomLevelDao;
    }
}
