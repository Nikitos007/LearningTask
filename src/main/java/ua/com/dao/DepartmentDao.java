package ua.com.dao;

import ua.com.model.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 11.07.17.
 */
public interface DepartmentDao {
    void save(Department department) throws SQLException;

    void delete(Department department) throws SQLException;

    List<Department> findAll() throws SQLException;

    Department getById(Long departmentId) throws SQLException;

    Department isExistDepartmentByName(Department departmentRequest) throws SQLException;


}
