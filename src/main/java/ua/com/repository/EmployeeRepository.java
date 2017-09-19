package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.models.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
}
