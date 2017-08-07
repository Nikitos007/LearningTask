package ua.com.dao.impl;


import ua.com.dao.EmployeeDao;
import ua.com.dao.Fields;
import ua.com.model.Employee;
import ua.com.utils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoDatababaseImpl implements EmployeeDao {

    private DBConnector dbConnector = new DBConnector();

    private String GET_BY_EMPLOYEE_ID = "SELECT * FROM tbl_employees WHERE id_employee = ?";
    private String GET_BY_DEPARTMENT_ID = "SELECT * FROM tbl_employees WHERE id_department = ?";
    private String DELETE_EMPLOYEE = "DELETE FROM tbl_employees WHERE id_employee = ?";
    private String INSERT_EMPLOYEE = "INSERT INTO tbl_employees(name, surname, hire_date, email, salary, id_department) VALUES(?, ?, ?, ?, ?, ?)";
    private String UPDATE_EMPLOYEE = "UPDATE tbl_employees SET name=?, surname=?, hire_date=?, email=?, salary=?, id_department=? WHERE id_employee = ?";
    private String GET_BY_EMAIL = "SELECT * FROM tbl_employees WHERE BINARY email=?";


    public void save(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbConnector.getConnection();
            if (employee.getId() == null) {
                preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);
                preparedStatement = setStatementToSave(preparedStatement, employee);
            } else {
                preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE);
                preparedStatement = setStatementToSave(preparedStatement, employee);
                preparedStatement.setLong(7, employee.getId());
            }
            preparedStatement.executeUpdate();
        }finally {
            dbConnector.closeStatement(preparedStatement);
            dbConnector.closeConnection(connection);
        }
    }

    private PreparedStatement setStatementToSave(PreparedStatement preparedStatement, Employee employee) throws SQLException {
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getSurname());
        preparedStatement.setDate(3, (Date) employee.getHireDate());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setInt(5, employee.getSalary());
        preparedStatement.setLong(6, employee.getDepartmentId());
        return preparedStatement;
    }

    public void delete(Employee employee) throws SQLException {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.executeUpdate();
        }
    }

    public List<Employee> getByDepartmentId(Long departmentId) throws SQLException {
        List<Employee> employeeList = new ArrayList();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_DEPARTMENT_ID)) {
            preparedStatement.setLong(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(extractEmployee(resultSet));
            }
            return employeeList;
        }
    }

    public Employee getById(Long employeeId) throws SQLException {
        Employee employee = null;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMPLOYEE_ID)) {
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = extractEmployee(resultSet);
            }
        }
        return employee;
    }

    public Employee isExistEmployeeByEmail(Employee employeeRequest) throws SQLException {
        Employee employee = null;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL)) {
            preparedStatement.setString(1, employeeRequest.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = extractEmployee(resultSet);
            }
        }
        return employee;
    }

    private Employee extractEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setDepartmentId(resultSet.getLong(Fields.EMPLOYEE_DEPARTMENT_ID.getField()));
        employee.setEmail(resultSet.getString(Fields.EMPLOYEE_EMAIL.getField()));
        employee.setHireDate(resultSet.getDate(Fields.EMPLOYEE_HIRE_DATE.getField()));
        employee.setId(resultSet.getLong(Fields.EMPLOYEE_ID.getField()));
        employee.setName(resultSet.getString(Fields.EMPLOYEE_NAME.getField()));
        employee.setSalary(resultSet.getInt(Fields.EMPLOYEE_SALARY.getField()));
        employee.setSurname(resultSet.getString(Fields.EMPLOYEE_SURNAME.getField()));
        return employee;
    }
}
