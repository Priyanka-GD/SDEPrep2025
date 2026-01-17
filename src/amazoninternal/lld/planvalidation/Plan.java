package amazoninternal.lld.planvalidation;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    List<Event> listOfEvents;

    public Plan() {
        this.listOfEvents = new ArrayList<>();
    }

    public void addEvent(Event event) {
        listOfEvents.add(event);
    }

    public List<Event> getEvents(){
        return listOfEvents;
    }
}