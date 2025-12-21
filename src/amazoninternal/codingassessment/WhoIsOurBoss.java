package amazoninternal.codingassessment;

import java.util.HashSet;
import java.util.Set;

public class WhoIsOurBoss {
    public static void main(String args[]) {
        // Create employees
        Employee ceo = new Employee();
        ceo.setId(1);
        ceo.setName("CEO");

        Employee managerA = new Employee();
        managerA.setId(2);
        managerA.setName("Manager A");
        managerA.setManager(ceo);

        Employee managerB = new Employee();
        managerB.setId(3);
        managerB.setName("Manager B");
        managerB.setManager(ceo);

        Employee emp1 = new Employee();
        emp1.setId(4);
        emp1.setName("Employee 1");
        emp1.setManager(managerA);

        Employee emp2 = new Employee();
        emp2.setId(5);
        emp2.setName("Employee 2");
        emp2.setManager(managerA);

        Employee emp3 = new Employee();
        emp3.setId(6);
        emp3.setName("Employee 3");
        emp3.setManager(managerB);

        Employee emp4 = new Employee();
        emp4.setId(7);
        emp4.setName("Employee 4");
        emp4.setManager(managerB);

        // ----------- TEST CASES ---------------

        System.out.println("Boss of emp1 & emp2      : " + whoIsOurBoss(emp1, emp2).getName()); // Manager A
        System.out.println("Boss of emp1 & emp3      : " + whoIsOurBoss(emp1, emp3).getName()); // CEO
        System.out.println("Boss of emp3 & emp4      : " + whoIsOurBoss(emp3, emp4).getName()); // Manager B
        System.out.println("Boss of managerA & emp3  : " + whoIsOurBoss(managerA, emp3).getName()); // CEO
        System.out.println("Boss of ceo & emp1       : " + whoIsOurBoss(ceo, emp1).getName()); // CEO
    }

    public static Employee whoIsOurBoss(Employee e1, Employee e2) {
        if (e1.getManager() == null) {
            //e1 is a manager
            return e1;
        }
        if (e2.getManager() == null) {
            //e2 is a manager
            return e2;
        }

        Set<Employee> e1Managers = findManager(e1);
        Employee temp = e2.getManager();
        while (temp != null) {
            if (e1Managers.contains(temp))
                return temp;
            temp = temp.getManager();
        }
        throw new IllegalStateException("Manager could not be found!!");
    }

    private static Set<Employee> findManager(Employee e1) {
        Set<Employee> managerSet = new HashSet<>();
        Employee temp = e1.getManager();
        while (temp != null) {
            managerSet.add(temp);
            temp = temp.getManager();
        }
        return managerSet;
    }
}
