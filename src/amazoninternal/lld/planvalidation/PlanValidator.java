package amazoninternal.lld.planvalidation;


import java.util.ArrayList;
import java.util.List;

public class PlanValidator {
    List<Plan> listOfPlans;
    List<Validation> validationsList;

    public PlanValidator() {
        listOfPlans = new ArrayList<>();
        validationsList = new ArrayList<>();
    }

    public void addPlan(Plan plan) {
        listOfPlans.add(plan);
    }

    public void addValidation(Validation validation) {
        validationsList.add(validation);
    }

    public void checkPlansValidations() {
        for (Plan plan : listOfPlans) {
            for (Validation validation : validationsList) {
                validation.validPlan(plan.getEvents());
            }
        }
    }

}
