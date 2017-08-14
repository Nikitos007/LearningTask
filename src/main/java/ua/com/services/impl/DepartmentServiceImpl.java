package ua.com.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.dao.DepartmentDao;
import ua.com.exception.ValidFieldException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.utils.validation.ValidationOval;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> viewAllDepartment() {
        return departmentDao.findAll();
    }

    public void delete(Department department) {
        departmentDao.delete(department);
    }

    public Department getDepartmentById(Long departmentId) {
        return departmentDao.getById(departmentId);
    }

    public void saveDepartment(Department department) throws ValidFieldException {
        ValidationOval.fieldsValidation(department);
        departmentDao.save(department);
    }
}
