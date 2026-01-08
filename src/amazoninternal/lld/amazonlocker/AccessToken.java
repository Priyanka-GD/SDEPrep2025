package amazoninternal.lld.amazonlocker;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

public class AccessToken {
    Date expirationDate;
    String token;
    Compartment compartment;

    public AccessToken(Date date, Compartment compartment) {
        this.expirationDate = Date.from(
                date.toInstant().plus(7, ChronoUnit.DAYS)
        );
        this.token = String.valueOf(UUID.randomUUID());
        this.compartment = compartment;
    }

    public Date getExpirationDate() {
        return expirationDate ;
    }

    public String getToken() {
        return token;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    boolean compartmentIsNotValid() {
        return expirationDate.before(new Date());
    }


}
