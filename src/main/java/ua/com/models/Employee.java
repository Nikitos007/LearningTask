package ua.com.models;

import net.sf.oval.constraint.*;
import ua.com.utils.validations.ValidationUniqueEmployeeEmail;

import javax.persistence.*;
import java.io.Serializable;

//        import ua.com.utils.validation.ValidationUniqueEmployeeEmail;

@Entity
@Table(name = "tbl_employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee")
    private Long employeeId;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @Length(min = 2, max = 100, message = "Surname length should be between 2 and 100")
    @MatchPattern(pattern = "[\\p{L}]+", message = "Incorrect name")
    @Column(name = "name", length = 100)
    private String name;

    @NotNull(message = "Surname can not be null")
    @NotEmpty(message = "Surname can not be empty")
    @Length(min = 2, max = 100, message = "Length should be between 2 and 100")
    @MatchPattern(pattern = "[\\p{L}]+", message = "Incorrect surname")
    @Column(name = "surname", length = 100)
    private String surname;

    @NotNull(message = "Hire date can not be null")
    @NotEmpty(message = "Hire date can not be empty")
    @MatchPattern(pattern = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])", message = "Incorrect date (yyyy-MM-dd)")
    @Column(name = "hire_date")
    private String hireDate;

    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @Length(min = 2, max = 100, message = "Email Length should be between 2 and 100")
    @CheckWith(value = ValidationUniqueEmployeeEmail.class, message = "This email has already exist")
    @MatchPattern(pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$", message = "Incorrect email")
    @Column(name = "email", length = 100)
    private String email;

    @NotNull(message = "Salary can not be null")
    @NotEmpty(message = "Salary can not be empty")
    @MatchPattern(pattern = "[0-9]+", message = "Incorrect format of salary")
    @Column(name = "salary")
    private Integer salary;

    @NotNull(message = "Department can not be null")
    @NotEmpty(message = "Department can not be empty")
    @ManyToOne(fetch = FetchType.LAZY)
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

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", hireDate=" + hireDate +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}