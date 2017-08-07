package ua.com.model;

import net.sf.oval.constraint.*;
import ua.com.utils.validation.ValidationUniqueDepartmentName;


public class Department {

    private Long id;

    @NotNull(message = "Can not be null")
    @NotEmpty(message = "Can not be empty")
    @Length(min = 2, max = 100, message = "Length should be between 2 and 100")
    @CheckWith(value = ValidationUniqueDepartmentName.class, message = "This name has already exist")
    @MatchPattern(pattern = "[\\p{L}]+", message = "Incorrect name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}