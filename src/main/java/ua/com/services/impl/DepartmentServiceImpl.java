package ua.com.services.impl;

import ua.com.dao.DepartmentDao;
import ua.com.dao.impl.DepartmentDaoHibernateImpl;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.validation.ValidationOval;

import java.sql.SQLException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao = new DepartmentDaoHibernateImpl();

    public List<Department> viewAllDepartment() throws SQLException {
        return departmentDao.findAll();
    }

    public void delete(Department department) throws SQLException {
        departmentDao.delete(department);
    }

    public Department getDepartmentById(Long departmentId) throws SQLException {
        return departmentDao.getById(departmentId);
    }

    public void saveDepartment(Department department) throws SQLException, ValidFieldException {
        ValidationOval.fieldsValidation(department);
        departmentDao.save(department);
    }
}
