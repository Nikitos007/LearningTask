package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.models.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    @Query("SELECT p FROM Employee p WHERE p.department.departmentId = ?1")
    List<Employee> findByDepartmentId(Long departmentId);

    @Query("SELECT p FROM Employee p WHERE p.email = ?1")
    Employee findByEmail(String email);
}
