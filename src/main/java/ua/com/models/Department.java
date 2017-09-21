package ua.com.models;

import net.sf.oval.constraint.*;
import ua.com.utils.validations.ValidationUniqueDepartmentName;
//        import ua.com.utils.validation.ValidationUniqueDepartmentName;

        import javax.persistence.*;
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

@Entity
@Table(name = "tbl_departments")
public class Department implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_department")
    private Long departmentId;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @Length(min = 2, max = 100, message = "Length should be between 2 and 100")
    @CheckWith(value = ValidationUniqueDepartmentName.class, message = "This name has already exist")
    @MatchPattern(pattern = "[\\p{L}]+", message = "Incorrect name")
    @Column(name = "name", length = 100)
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    List<Employee> employeeList = new ArrayList<>();

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String name) {
        this.departmentName = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return departmentId != null ? departmentId.equals(that.departmentId) : that.departmentId == null;
    }

    @Override
    public int hashCode() {
        return departmentId != null ? departmentId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", employeeList=" + employeeList +
                '}';
    }
}