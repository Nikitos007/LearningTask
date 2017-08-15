package ua.com.dao;

import ua.com.model.Department;

/**
 * Created on 11.07.17.
 */
public interface DepartmentDao extends CRUDOperationsDao<Department, Long> {

    Department getByName(String name);

}
