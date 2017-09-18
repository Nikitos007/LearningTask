package ua.com.services;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.models.Department;
import ua.com.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public void save(Department department) {
        departmentRepository.save(department);
    }

    public void remove(Department department) {
        departmentRepository.delete(department);
    }




public String sayHello() {
            return "HeLLO FROM SERVICE DEPARTMENT";
    }


}
