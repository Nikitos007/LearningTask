package ua.com.services;

import ua.com.exception.ValidFieldException;
import ua.com.model.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 13.07.17.
 */
public interface DepartmentService {
    List<Department> viewAllDepartment() throws SQLException;

    void delete(Department department) throws SQLException;

    Department getDepartmentById(Long departmentId) throws SQLException;

    void saveDepartment(Department department) throws SQLException, ValidFieldException;
}
