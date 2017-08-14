package ua.com.dao;

import ua.com.model.Employee;

import java.util.List;

/**
 * Created on 11.07.17.
 */
public interface EmployeeDao extends CRUDOperationsDao<Employee, Long> {

    List<Employee> getByDepartmentId(Long departmentId);

    Employee getByEmail(String email);
}
