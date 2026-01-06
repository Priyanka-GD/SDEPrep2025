package amazoninternal.lld.employeeOrganization;

public class Main {
    public static void main(String[] args) {
        Employee ceo = new Employee("Alice", "CEO", 5);
        Employee director = new Employee("Bob", "Director", 4);
        Employee manager = new Employee("Charlie", "Manager", 3);
        Employee sde1 = new Employee("David", "SDE", 2);
        Employee sde2 = new Employee("Eva", "SDE", 2);
        Employee intern = new Employee("Isha", "Intern", 1);

        ManagerManagement org = new ManagerManagement();

        org.assignManager(ceo, director);
        org.assignManager(director, manager);
        org.assignManager(manager, sde1);
        org.assignManager(manager, sde2);
        org.assignManager(sde1, intern);

        ceo.printHierarchy();

        // Reassign example (still works)
        System.out.println("\n--- After reassign Eva under Director ---");
        org.assignManager(director, sde2);
        ceo.printHierarchy();
    }
}
