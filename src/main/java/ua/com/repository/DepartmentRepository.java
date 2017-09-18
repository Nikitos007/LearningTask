package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.models.Department;

/**
 * Created on 18.09.17.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
