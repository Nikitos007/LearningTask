package ua.com.dao;

import ua.com.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 11.07.17.
 */
public interface EmployeeDao {
    void save(Employee employee) throws SQLException;

    void delete(Employee employee) throws SQLException;

    List<Employee> getByDepartmentId(Long departmentId) throws SQLException;

    Employee getById(Long employeeId) throws SQLException;

    Employee isExistEmployeeByEmail(Employee employee) throws SQLException;
}
