package amazoninternal.codingassessment;

import java.util.Set;

public class Employee {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getReports() {
        return reports;
    }

    public void setReports(Set<Employee> reports) {
        this.reports = reports;
    }

    long id;
    String name;
    Employee manager;
    Set<Employee> reports;
}
