package ua.com.utils.validation;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.dao.DepartmentDao;
import ua.com.model.Department;

@Component
public class ValidationUniqueDepartmentName implements CheckWithCheck.SimpleCheck {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public boolean isSatisfied(Object departmentObj, Object value) {
        Department departmentRequest = (Department) departmentObj;
        Department department = departmentDao.getByName(departmentRequest.getDepartmentName());
        if (department == null) {
            return true;
        }
        return departmentRequest.getDepartmentId() != null && department.getDepartmentId() != null && department.getDepartmentId().equals(departmentRequest.getDepartmentId());
    }
}

