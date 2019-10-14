package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.RoomPhotoBiz;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.dao.RoomPhotoDao;
import com.bateng.guestroom.entity.RoomPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roomPhotoBiz")
public class RoomPhotoBizImpl implements RoomPhotoBiz {

    @Autowired
    private RoomPhotoDao roomPhotoDao;
    @Override
    public RoomPhoto getRoomPhotoById(int id) {
        return roomPhotoDao.getOne(id);
    }

    @Override
    @Transactional
    public void deleteRoomPhotoById(int id) {
        roomPhotoDao.deleteById(id);
    }

    public RoomPhotoDao getRoomPhotoDao() {
        return roomPhotoDao;
    }

    public void setRoomPhotoDao(RoomPhotoDao roomPhotoDao) {
        this.roomPhotoDao = roomPhotoDao;
    }
}
