package amazoninternal.lld.elevatormanagementsystem;

public enum RequestType {
    PICKUP_UP,
    PICKUP_DOWN,
    DESTINATION
}

/*
* Hall call button exists in every floor, it has up or down direction
* User when presses a hall call, it registers a request from the current floor + direction(up or down)
* Once user gets inside the elevator, user can then request a destination floor
* so there are three requesttype UP, DOWN or DESTINATION
* */
