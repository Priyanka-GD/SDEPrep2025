package amazoninternal.lld.employeeOrganization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerManagement {
    Map<Employee, Employee> employeeToManager;

    public ManagerManagement() {
        employeeToManager = new HashMap<>();

    }

    public boolean assignManager(Employee manager, Employee employee) {
        if (manager == null || employee == null)
            return false;
        if (manager.level <= employee.level)
             return false;

        // If employee already had a manager, remove from old manager's reportees
        Employee formerManager = employeeToManager.get(employee);
        if (formerManager != null) {
            formerManager.removeReportee(employee);
        }

        // Add to new manager
        manager.addReportee(employee);

        // Update reverse mapping
        employeeToManager.put(employee, manager);
        return true;
    }
}
