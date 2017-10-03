package ua.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.sf.oval.constraint.*;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.utils.validation.ValidationUniqueEmployeeEmail;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee")
    private Long employeeId;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @Length(min = 2, max = 100, message = "Length should be between 2 and 100")
    @MatchPattern(pattern = "[\\p{L}]+", message = "Incorrect name")
    @Column(name = "name", length = 100)
    private String name;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @Length(min = 2, max = 100, message = "Length should be between 2 and 100")
    @MatchPattern(pattern = "[\\p{L}]+", message = "Incorrect surname")
    @Column(name = "surname", length = 100)
    private String surname;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "hire_date")
    private Date hireDate;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @Length(min = 2, max = 100, message = "Length should be between 2 and 100")
    @CheckWith(value = ValidationUniqueEmployeeEmail.class, message = "This email has already exist")
    @MatchPattern(pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$", message = "Incorrect email")
    @Column(name = "email", length = 100)
    private String email;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @Column(name = "salary")
    private Integer salary;

    @JsonIgnore
    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_department")
    private Department department;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return employeeId != null ? employeeId.equals(employee.employeeId) : employee.employeeId == null;
    }

    @Override
    public int hashCode() {
        return employeeId != null ? employeeId.hashCode() : 0;
    }

//    @Override
//    public String toString() {
//        return "Employee{" +
//                "employeeId=" + employeeId +
//                ", name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", hireDate=" + hireDate +
//                ", email='" + email + '\'' +
//                ", salary=" + salary +
//                ", department=" + department +
//                '}';
//    }
}
