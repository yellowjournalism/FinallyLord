package entity;

public enum EntityFlag {
    POINT_MOVE_DOWN, POINT_MOVE_UP,//Places to move up/down (to allow for stairs to meet each other on different levels
    CAN_BLOCK,//Will this sometimes be a blocking tile? So future entities like in-world items dont explode when a door closes on them.
    FLAG_MAX;//Keep this last!
}
