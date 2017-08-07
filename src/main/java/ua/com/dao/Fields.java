package ua.com.dao;

/**
 * Created on 12.07.17.
 */
public enum Fields {

    DEPARTMENT_ID("id_department"),
    DEPARTMENT_NAME("name"),
    EMPLOYEE_ID("id_employee"),
    EMPLOYEE_NAME("name"),
    EMPLOYEE_SURNAME("surname"),
    EMPLOYEE_HIRE_DATE("hire_date"),
    EMPLOYEE_EMAIL("email"),
    EMPLOYEE_SALARY("salary"),
    EMPLOYEE_DEPARTMENT_ID("id_department");

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    Fields(String field) {
        this.field = field;
    }


}
