Challenge: Elevator Control System
Design the Low-Level Design (LLD) for a multi-elevator system in a high-rise building. 
The focus here is on request scheduling, state management, and movement logic.

System Requirements
Elevator Components:

The building has N elevators and M floors.

Each elevator has a maximum capacity (weight or person count).

Each elevator has a display showing the current floor and direction (Up, Down, Idle).

Request Handling:

Internal Requests: A passenger inside the elevator presses a button for a destination floor.

External Requests: A person on a floor presses a button to go Up or Down.

Dispatching Algorithm:

The system must decide which elevator is the most "optimal" to pick up a passenger from a floor.

Consider factors like: Is the elevator already moving in that direction? Is it the closest one? Is it idle?

Safety & States:

Elevators can be in various states: Moving, Idle, Door Opening, Door Closed, Under Maintenance.

The system must handle emergency triggers (e.g., "Emergency Stop" or "Overweight" alarm).

Door Logic:

Doors should remain open for a fixed duration.

Passengers can trigger "Open Door" or "Close Door" manually from the inside.

Entities :

1. Elevator - int capacity, ElevatorMovement, ElevatorState, int currentFloor, int timer(Door close), PriorityList of Stops 
2. ElevatorMovement - enum (UP, DOWN, IDLE)
3. ElevatorState - interface with methods (Moving, Idle, Door Opening, Door Closed, Under Maintenance)
4. Every state needs to implement methods and make the transition from one state to another
5. ElevatorManager - 
     a. Will have Map of floorNumber with the Elevator in that floor - floorMap
     b. Will have Map of ElevatorMovement with the list of Elevators in that movement - MovementMap
     b. When a user presses the elevator button to go UP/DOWN, it searches in the 
         i. MovementMap - if any elevator is at DOWN/UP state closest to it then send it, else send the one from idle state
    
