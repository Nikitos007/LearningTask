package ua.com.dao.impl;

import ua.com.dao.DepartmentDao;
import ua.com.dao.Fields;
import ua.com.model.Department;
import ua.com.utils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 11.07.17.
 */
public class DepartmentDaoDatabaseImpl implements DepartmentDao {

    private DBConnector dbConnector = new DBConnector();

    private String FIND_ALL = "SELECT * FROM tbl_departments";
    private String DELETE_DEPARTMENT = "DELETE FROM  tbl_departments WHERE id_department=?";
    private String GET_DEPARTMENT_BY_ID = "SELECT * FROM tbl_departments WHERE id_department=?";
    private String GET_DEPARTMENT_BY_NAME = "SELECT * FROM tbl_departments WHERE BINARY name=?";
    private String SAVE_DEPARTMENT = "INSERT INTO tbl_departments(name) VALUES (?)";
    private String UPDATE_DEPARTMENT = "UPDATE tbl_departments SET name=? WHERE id_department=?";

    public List<Department> findAll() throws SQLException {
        List<Department> departmentList = new ArrayList<Department>();
        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                departmentList.add(extractDepartment(resultSet));
            }
            return departmentList;
        }
    }

    public void save(Department department) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbConnector.getConnection();
            if (department.getId() == null) {
                preparedStatement = connection.prepareStatement(SAVE_DEPARTMENT);
                preparedStatement.setString(1, department.getName());
            } else {
                preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENT);
                preparedStatement.setString(1, department.getName());
                preparedStatement.setLong(2, department.getId());
            }
            preparedStatement.executeUpdate();
        } finally {
            dbConnector.closeStatement(preparedStatement);
            dbConnector.closeConnection(connection);
        }
    }

    public void delete(Department department) throws SQLException {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENT)) {
            preparedStatement.setLong(1, department.getId());
            preparedStatement.executeUpdate();
        }
    }

    public Department getById(Long departmentId) throws SQLException {
        Department department = null;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department = extractDepartment(resultSet);
            }
        }
        return department;
    }

    public Department isExistDepartmentByName(Department departmentRequest) throws SQLException {
        Department department = null;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DEPARTMENT_BY_NAME)) {
            preparedStatement.setString(1, departmentRequest.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department = extractDepartment(resultSet);
            }
        }
        return department;
    }

    private Department extractDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong(Fields.DEPARTMENT_ID.getField()));
        department.setName(resultSet.getString(Fields.DEPARTMENT_NAME.getField()));
        return department;
    }

}
