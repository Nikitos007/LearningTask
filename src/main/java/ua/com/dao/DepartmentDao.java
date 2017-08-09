package ua.com.dao;

import ua.com.model.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 11.07.17.
 */
public interface DepartmentDao extends CRUDOperationsDao<Department, Long>{

    Department getByName(Department department);

}
