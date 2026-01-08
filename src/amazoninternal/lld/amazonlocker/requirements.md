Requirements:
1. Carrier deposits a package by specifying size (small, medium, large)
    - System assigns an available compartment of matching size
    - Returns compartment number and access token, or error if no space
2. Upon successful deposit, an access token is generated and returned
    - One access token per package
3. User retrieves package by entering access token
    - System validates code and returns compartment number
    - Throws specific error if code is invalid or expired
4. Access tokens expire after 7 days
    - Expired codes are rejected if used for pickup
    - Package remains in compartment until staff removes it
5. Staff can remove expired packages to free up compartments
    - Returns the package to sender and makes compartment available
6. Invalid access tokens are rejected with clear error messages
    - Wrong code, already used, or expired - user gets specific feedback

This design follows SRP, encapsulation, separation of concerns, low coupling, and high cohesion. It’s a solid base LLD. 
Patterns can be added later if requirements grow.

Out of scope:
- How the package gets to the locker (delivery logistics)
- How the access token reaches the customer (SMS/email notification)
- Lockout after failed access token attempts
- UI/rendering layer
- Multiple locker stations
- Payment or pricing