package amazoninternal.lld.amazonlocker;

import java.util.*;

public class Locker {
    List<Compartment> compartmentList = new ArrayList<>();
    Map<String, AccessToken> accessTokenMap = new HashMap<>();

    public List<Compartment> getCompartmentList() {
        return compartmentList;
    }

    public void addCompartment(Compartment compartment) {
        this.compartmentList.add(compartment);
    }

    public void setCompartmentAccessTokenMap(String token, AccessToken accessToken) {
        this.accessTokenMap.put(token, accessToken);
    }

    public TokenRecord depositPackage(Size size) {
        Compartment compartment = getAvailableCompartment(size);
        if (compartment == null) {
            throw new Error("Compartment not available for " + size);
        }
        AccessToken accessToken = generateValidToken(compartment);
        compartment.markOccupied();
        accessTokenMap.put(accessToken.getToken(), accessToken);
        return new TokenRecord(compartment.getCompartmentId(), accessToken.getToken());
    }

    public int pickUp(String token) {
        AccessToken accessToken = accessTokenMap.get(token);
        if (accessToken == null) {
            throw new Error("Invalid token");
        }

        if (accessToken.compartmentIsNotValid()) {
            throw new Error("Access Token has expired");
        }

        Compartment compartment = accessToken.getCompartment();
        clearDeposit(compartment, token);
        return compartment.getCompartmentId();
    }

    public void clearDeposit(Compartment compartment, String token) {
        compartment.markFree();
        accessTokenMap.remove(token);
    }

    private AccessToken generateValidToken(Compartment compartment) {
        return new AccessToken(new Date(), compartment);
    }

    private Compartment getAvailableCompartment(Size size) {
        for (Compartment compartment : compartmentList) {
            if (!compartment.isOccupied && compartment.size.equals(size)) {
                return compartment;
            }
        }
        return null;
    }
}
