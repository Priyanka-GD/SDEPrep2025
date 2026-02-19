package amazoninternal.lld.elevatormanagementsystem;

import java.util.HashSet;
import java.util.Set;

public class Elevator {
    Set<Request> requestSet = new HashSet<>();
    int currentFloor;
    Direction direction;

    public boolean addRequest(int floor, RequestType requestType) {
        Request request = new Request(floor, requestType);
        if (!requestSet.contains(request)) {
            requestSet.add(request);
            return true;
        }
        return false;
    }

    public void step() {
        //if no requests in queue then set the direction of elevator to be IDLE
        if (requestSet.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        if (shouldStopAtCurrentFloor()) {
            clearRequestAndMoveForward();
            //The elevator "stood still" for one cycle to simulate people getting on/off.
            return;
        }

        if (direction == Direction.IDLE) {
            //Depending on the next floor , determine the direction comparing with the currentFloor
            determineDirection();
        }

        currentFloor += direction == Direction.UP ? +1 : -1;
    }

    private void determineDirection() {
        Request next = requestSet.iterator().next();
        direction = next.floor > currentFloor ? Direction.UP : Direction.DOWN;
    }

    private void clearRequestAndMoveForward() {
        requestSet.remove(new Request(currentFloor, RequestType.PICKUP_UP));
        requestSet.remove(new Request(currentFloor, RequestType.PICKUP_DOWN));
        requestSet.remove(new Request(currentFloor, RequestType.DESTINATION));
        if (requestSet.isEmpty()) {
            direction = Direction.IDLE;
        } else if (!hasRequestsInDirection(direction)) {
            // Look the other way if there's nothing left ahead
            direction = Direction.IDLE; // Next step() will re-determine direction
        }
    }

    private boolean hasRequestsInDirection(Direction dir) {
        return requestSet.stream().anyMatch(r ->
                (dir == Direction.UP && r.floor > currentFloor) ||
                        (dir == Direction.DOWN && r.floor < currentFloor));
    }

    private boolean shouldStopAtCurrentFloor() {
        RequestType pickUpType = (direction == Direction.UP) ? RequestType.PICKUP_UP : RequestType.PICKUP_DOWN;
        //Check for pick up or down requests from requestSet
        boolean hasPickUpType = requestSet.contains(new Request(currentFloor, pickUpType));
        //If no pick up from below or upper floors then check for drop off requests
        boolean hasDropOff = requestSet.contains(new Request(currentFloor, RequestType.DESTINATION));
        return hasPickUpType || hasDropOff || (direction == Direction.IDLE && hasAnyRequestAtFloor());
    }

    // See if any current floor request exists in requestSet queue
    private boolean hasAnyRequestAtFloor() {
        return requestSet.stream().anyMatch(r -> r.floor == currentFloor);
    }


}
