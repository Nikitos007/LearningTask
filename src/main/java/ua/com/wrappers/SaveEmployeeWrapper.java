package ua.com.wrappers;

import ua.com.model.Employee;

/**
 * Created on 03.10.17.
 */
public class SaveEmployeeWrapper {

    private Employee employee;
    private Long departmentId;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
