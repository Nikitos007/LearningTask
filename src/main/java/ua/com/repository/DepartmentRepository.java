package ua.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.models.Department;

import javax.annotation.PostConstruct;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

//    @Query(" from LoginDetails as o where o.userName=:username and o.password = :userPasswd")
//    LoginDetails findByUsername(String username, String userPasswd);

}
