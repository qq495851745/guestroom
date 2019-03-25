package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.RoomPhoto;

public interface RoomPhotoBiz {

    public RoomPhoto getRoomPhotoById(int id);

    public void deleteRoomPhotoById(int id);
}
