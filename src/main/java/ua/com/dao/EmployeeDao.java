package ua.com.dao;

import ua.com.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 11.07.17.
 */
public interface EmployeeDao extends CRUDOperationsDao<Employee, Long> {

    List<Employee> getByDepartmentId(Long departmentId) throws SQLException;

    Employee getByEmail(Employee employee);
}
