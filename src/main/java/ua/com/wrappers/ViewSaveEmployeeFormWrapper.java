package ua.com.wrappers;

import ua.com.model.Department;
import ua.com.model.Employee;

import java.util.List;

public class ViewSaveEmployeeFormWrapper {

    private Employee employee;
    private Department department;
    private List<Department> departmentList;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

}
