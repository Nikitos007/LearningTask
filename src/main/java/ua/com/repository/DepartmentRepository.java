package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.models.Department;
import ua.com.models.Employee;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT p FROM Department p WHERE p.departmentName = ?1")
    Department findByName(String name);

}
