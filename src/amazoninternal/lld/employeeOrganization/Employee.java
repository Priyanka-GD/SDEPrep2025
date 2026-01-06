package amazoninternal.lld.employeeOrganization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee {
    final String name;
    final String designation;
    final int level;
    final List<Employee> reportees;

    public Employee(String name, String designation, int level) {
        this.name = name;
        this.designation = designation;
        this.level = level;
        this.reportees = new ArrayList<>();
    }

    public List<Employee> getReportees() {
        return Collections.unmodifiableList(reportees);
    }

    // Package-private helpers so only the org manager mutates the tree
    void addReportee(Employee e) {
        if (!reportees.contains(e)) reportees.add(e);
    }

    void removeReportee(Employee e) {
        reportees.remove(e);
    }

    public void printHierarchy() {
        printHierarchy(0);
    }

    private void printHierarchy(int depth) {
        for (int i = 0; i < depth; i++) System.out.print("   ");
        System.out.println(designation + " - " + name + " (L" + level + ")");

        for (Employee r : reportees) {
            r.printHierarchy(depth + 1);
        }
    }
}
