package amazoninternal.lld.amazonlocker;

public class TokenRecord {
    public TokenRecord(int compartmentId, String token) {
        this.compartmentId = compartmentId;
        this.token = token;
    }

    int compartmentId;
    String token;


}
