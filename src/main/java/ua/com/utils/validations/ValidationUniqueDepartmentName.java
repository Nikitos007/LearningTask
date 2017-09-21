package ua.com.utils.validations;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.models.Department;
import ua.com.repository.DepartmentRepository;

@Component
public class ValidationUniqueDepartmentName implements CheckWithCheck.SimpleCheck {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public boolean isSatisfied(Object departmentObj, Object value) {
        Department departmentRequest = (Department) departmentObj;
        Department department = departmentRepository.findByName(departmentRequest.getDepartmentName());
        if (department == null) {
            return true;
        }
        return departmentRequest.getDepartmentId() != null && department.getDepartmentId() != null && department.getDepartmentId().equals(departmentRequest.getDepartmentId());
    }
}