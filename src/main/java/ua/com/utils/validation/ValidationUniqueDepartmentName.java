package ua.com.utils.validation;

import net.sf.oval.constraint.CheckWithCheck;
import org.apache.log4j.Logger;
import ua.com.dao.DepartmentDao;
import ua.com.dao.impl.DepartmentDaoDatabaseImpl;
import ua.com.model.Department;

import java.sql.SQLException;


public class ValidationUniqueDepartmentName implements CheckWithCheck.SimpleCheck {

    private static final Logger LOG = Logger.getLogger(ValidationUniqueDepartmentName.class);
    private static DepartmentDao departmentDao = new DepartmentDaoDatabaseImpl();

    @Override
    public boolean isSatisfied(Object departmentObj, Object value) {
        try {
            Department departmentRequest = (Department) departmentObj;
            Department department = departmentDao.isExistDepartmentByName(departmentRequest);
            if (department.getId() == null) {
                return true;
            }
            if (departmentRequest.getId() != null && department.getId() != null && department.getId() == departmentRequest.getId()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

}
