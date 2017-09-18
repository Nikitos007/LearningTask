package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.models.Department;
import ua.com.models.Employee;

/**
 * Created on 18.09.17.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
