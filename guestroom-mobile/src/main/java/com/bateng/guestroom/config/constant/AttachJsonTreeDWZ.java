package com.bateng.guestroom.config.constant;

public class AttachJsonTreeDWZ {
    //RoomOption
    public static final String ROOM_OPTION_TREE_REL="jbsxBox";
    public static final String ROOM_OPTION_TREE_HREF="option/show/";

    //RoomLevel

    public  static final String ROOM_LEVEL_TREE_REL="roomLevelBox";
    public  static final String ROOM_LEVEL_TREE_HREF="guestroom/roomlevel/show/";

    //room 属性
    public static class RoomDWZ{
        public static final String ROOM_TREE_REL="roomBox";
        public static final String ROOM_TREE_HREF="guestroom/room/roomLevel/show/";

        public static  final String DECLARATION_ROOM_TREE_REL="declarationForm_add_lookup_room_box";
        public static  final String  DECLARATION_ROOM_TREE_HREF="guestroom/declarationForm/roomLevel/room/show/";
    }





    //UserLevel
     public static class  UserLevelDWZ{
        public final static String USER_LEVEL_TREE_HREF="guestroom/userLevel/show/";
        public final static String USER_LEVEL_TREE_REL="userLevelBox";
    }

}
