package com.bateng.guestroom.config.convert;

import com.bateng.guestroom.entity.RoomAndRoomLevel;
import com.bateng.guestroom.entity.RoomLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class StringToRoomLevelsConvert implements Converter<String, List<RoomAndRoomLevel>>{

    private Logger logger= LoggerFactory.getLogger(StringToRoomLevelsConvert.class);
    @Override
    public List<RoomAndRoomLevel> convert(String s) {
        List<RoomAndRoomLevel> roomAndRoomLevels=new ArrayList<RoomAndRoomLevel>();
        try {
            String[] ides=s.split(",");
            for(String id:ides){
                RoomLevel roomLevel=new RoomLevel();
                roomLevel.setId(new Integer(id));
                RoomAndRoomLevel roomAndRoomLevel=new RoomAndRoomLevel();
                roomAndRoomLevel.setRoomLevel(roomLevel);
                roomAndRoomLevels.add(roomAndRoomLevel);
            }
        } catch (NumberFormatException e) {
            logger.error("异常",e);
            return null;
        }
        return roomAndRoomLevels;
    }
}
