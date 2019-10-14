package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.RoomPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomPhotoDao extends JpaRepository<RoomPhoto,Integer> {


}
