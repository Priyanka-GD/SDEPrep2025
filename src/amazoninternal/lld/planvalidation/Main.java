package amazoninternal.lld.planvalidation;

public class Main {
    public static void main(String[] args) {
        Plan plan = getPlan();
        PlanValidator planManager = new PlanValidator();
        planManager.addPlan(plan);
        planManager.addValidation(new TimeStrictlyIncreasingValidation());
        planManager.addValidation(new PalletsPickedUpBeforeDroppedOff());
        planManager.addValidation(new AllPalletsPickedUpAndDroppedOff());

        planManager.checkPlansValidations();
    }

    private static Plan getPlan() {
        Event event1 = new Event(1, Operation.PICKUP, 1, "A");
        Event event2 = new Event(2, Operation.PICKUP, 2, "B");
        Event event3 = new Event(3, Operation.DROP, 1, "C");
        Event event4 = new Event(3, Operation.DROP, 2, "C");
        Event event5 = new Event(3, Operation.PICKUP, 3, "C");
        //Event event6 = new Event(4, Operation.DROP, 3, "D");

        Plan plan = new Plan();
        plan.addEvent(event1);
        plan.addEvent(event2);
        plan.addEvent(event3);
        plan.addEvent(event4);
        plan.addEvent(event5);
        return plan;
    }
}
