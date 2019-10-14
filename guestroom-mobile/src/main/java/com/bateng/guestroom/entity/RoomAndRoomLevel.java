package com.bateng.guestroom.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 房间，和房间层级关联表
 */
@Entity
@Table(name="t_room_and_room_level")
public class RoomAndRoomLevel {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name="room_level_id")
    private  RoomLevel roomLevel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomLevel getRoomLevel() {
        return roomLevel;
    }

    public void setRoomLevel(RoomLevel roomLevel) {
        this.roomLevel = roomLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAndRoomLevel that = (RoomAndRoomLevel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
