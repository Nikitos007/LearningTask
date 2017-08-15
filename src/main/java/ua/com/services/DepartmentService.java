package ua.com.services;

import ua.com.exception.ValidFieldException;
import ua.com.model.Department;

import java.util.List;

/**
 * Created on 13.07.17.
 */
public interface DepartmentService {
    List<Department> viewAllDepartment();

    void delete(Department department);

    Department getDepartmentById(Long departmentId);

    void saveDepartment(Department department) throws ValidFieldException;
}
