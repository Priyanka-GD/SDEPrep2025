package amazoninternal.lld.elevatormanagementsystem;

public class Request {
    int floor;
    RequestType requestType;

    public Request(int floor, RequestType requestType) {
        this.requestType = requestType;
        this.floor = floor;
    }

    public Request addRequest(int floor, RequestType requestType) {
        return new Request(floor, requestType);
    }

}
/**
 * When a user requests for an elevator with direction from a floor, request object is created
 * and gets assigned to an elevator with type being UP or DOWN
 * When a user presses a destination floor inside the elevator, another request gets registered with type DESTINATION
 */
