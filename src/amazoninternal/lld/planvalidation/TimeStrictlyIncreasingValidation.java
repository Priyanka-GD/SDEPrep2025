package amazoninternal.lld.planvalidation;

import java.util.List;

public class TimeStrictlyIncreasingValidation implements Validation {

    @Override
    public void validPlan(List<Event> events) {
        if (events == null)
            throw new IllegalArgumentException("Events is empty");


        for (int idx = 0; idx < events.size() - 1; idx++) {
            Event curr = events.get(idx);
            Event next = events.get(idx + 1);

            if (next.time < curr.time) {
                throw new IllegalArgumentException("Validation Failed: Time went backwards at index " + (idx + 1));
            }
            if (curr.time == next.time) {
                boolean isDuplicate = curr.location.equals(next.location);

                if (!isDuplicate) {
                    throw new IllegalArgumentException("Validation Failed: Multiple distinct events at time " + curr.time);
                }
            }
        }
        System.out.println("TimeStrictlyIncreasingValidation is validated");
    }
}