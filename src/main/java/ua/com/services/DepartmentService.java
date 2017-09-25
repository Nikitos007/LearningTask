package ua.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.exceptions.ValidateException;
import ua.com.models.Department;
import ua.com.repository.DepartmentRepository;
import ua.com.utils.validations.ValidationOval;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public void validate(Department department) throws ValidateException {
        ValidationOval.fieldsValidation(department);
    }

    public void save(Department department) throws ValidateException {
        validate(department);
        departmentRepository.save(department);
    }

    public void delete(Department department) {
        departmentRepository.delete(department);
    }

}
