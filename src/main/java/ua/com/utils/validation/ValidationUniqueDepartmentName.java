package ua.com.utils.validation;

import net.sf.oval.constraint.CheckWithCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.dao.DepartmentDao;
import ua.com.dao.impl.DepartmentDaoHibernateImpl;
import ua.com.model.Department;


public class ValidationUniqueDepartmentName implements CheckWithCheck.SimpleCheck {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationUniqueDepartmentName.class);
    private static DepartmentDao departmentDao = new DepartmentDaoHibernateImpl();

    @Override
    public boolean isSatisfied(Object departmentObj, Object value) {
        Department departmentRequest = (Department) departmentObj;
        Department department = departmentDao.getByName(departmentRequest);
        if (department == null) {
            return true;
        }
        return departmentRequest.getId() != null && department.getId() != null && department.getId().equals(departmentRequest.getId());
    }
}

